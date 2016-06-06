package org.huntsboro.admin.services;

import java.util.List;

import org.huntsboro.admin.dto.LoginDTO;
import org.huntsboro.data.dao.ApplicantsAndTenantsRepository;
import org.huntsboro.data.dao.ApplicationsAndLeasesRepository;
import org.huntsboro.data.dao.ResidenceAndRentalHistoryRepository;
import org.huntsboro.data.entity.ApplicantsAndTenants;
import org.huntsboro.data.entity.ResidenceAndRentalHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/applicantsandtenants")
@RestController
public class ApplicantsAndTenantsResource {
	
	@Autowired
	private ApplicantsAndTenantsRepository repo ;
	@Autowired
	private ApplicationsAndLeasesRepository leasesRepo ;
	@Autowired
	private ResidenceAndRentalHistoryRepository rentalHistoryRepo;

    public ApplicantsAndTenantsResource() {
    }

    @RequestMapping(value="/",method=RequestMethod.POST,consumes={"application/xml", "application/json"})
    @Transactional
    public ResponseEntity<ApplicantsAndTenants> create(@RequestBody ApplicantsAndTenants entity,UriComponentsBuilder ucBuilder) {
    	
    	try {
			String password = entity.getPassword();
			String encryptedPassword = PasswordEncryptionService.encrypt(password);
			entity.setPassword(encryptedPassword.toString());
			System.out.println("storing" + encryptedPassword.toString());
			repo.save(entity);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/applicantsandtenant/{id}").buildAndExpand(entity.getId().toString()).toUri());
			//entity.setPassword(password);
			return new ResponseEntity<ApplicantsAndTenants>(entity, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
		}
    	return new ResponseEntity<ApplicantsAndTenants>(entity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value="/",method=RequestMethod.PUT,consumes = {"application/xml", "application/json"})
    @Transactional  
    public ResponseEntity<ApplicantsAndTenants> edit(ApplicantsAndTenants entity) {
    	ApplicantsAndTenants  one = repo.findOne(entity.getId());
    	if(one ==null){
    		return new ResponseEntity<ApplicantsAndTenants>(HttpStatus.NOT_FOUND);
    	}
    	repo.delete(one);
        return new ResponseEntity<ApplicantsAndTenants>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}",method=RequestMethod.DELETE,consumes = {"application/xml", "application/json"})
    @Transactional
    public ResponseEntity<ApplicantsAndTenants> remove(@PathVariable("id") Integer id) {
    	ApplicantsAndTenants  entity = repo.findOne(id);
        if(entity ==null){
    		return new ResponseEntity<ApplicantsAndTenants>(HttpStatus.NOT_FOUND);
    	}
        repo.delete(entity);
        return new ResponseEntity<ApplicantsAndTenants>(HttpStatus.NO_CONTENT);
    }

    
    @RequestMapping(value="/{id}",method=RequestMethod.GET,produces = {"application/xml", "application/json"})
    @Transactional
    public ResponseEntity<ApplicantsAndTenants> find(@PathVariable("id") Integer id) {
    	org.huntsboro.data.entity.ApplicantsAndTenants one= repo.findOne(id);
        if(one ==null)
        	return new ResponseEntity<ApplicantsAndTenants>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<ApplicantsAndTenants>(one,HttpStatus.OK);
    }

    @RequestMapping(value="/",method=RequestMethod.GET, produces={"application/xml","application/json"} )
    @Transactional
    @ResponseBody
    public ResponseEntity<List<ApplicantsAndTenants>> findAll() {
        List<ApplicantsAndTenants> list = repo.findAll();
        if(list.isEmpty())
        	return new ResponseEntity<List<ApplicantsAndTenants>>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<ApplicantsAndTenants>>(list,HttpStatus.OK);
    }

   
    @RequestMapping(value="/Properties/{p_id}/Units/{u_id}",method=RequestMethod.GET,produces = {"application/xml", "application/json"})
    @Transactional
    public ResponseEntity<List<ResidenceAndRentalHistory>> getRentalHistory(@PathVariable("id") Integer id) {
    	List<ResidenceAndRentalHistory> rentalHistories = rentalHistoryRepo.findByTenantId(id);
        if(CollectionUtils.isEmpty(rentalHistories))
        	return new ResponseEntity<List<ResidenceAndRentalHistory>>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<ResidenceAndRentalHistory>>(rentalHistories,HttpStatus.OK);
    }
 
    
    @RequestMapping(value="/auth",method=RequestMethod.POST,produces = {"application/xml", "application/json"})
    @Transactional
    public ResponseEntity<ApplicantsAndTenants> authenticate(@RequestBody LoginDTO dto ) {
    	ApplicantsAndTenants tenant = null;
    	String encryptedPassword =null;
    	String attemptedPassword = dto.getPassword();
    	 try {
    		encryptedPassword = PasswordEncryptionService.encrypt(attemptedPassword);
    		System.out.println("encrypted -" + encryptedPassword.toString());
    		
		} catch(Exception e)
    	{
			e.printStackTrace();
    	}

    	tenant = repo.findByEmailAndPassword(dto.getEmail(), encryptedPassword.toString());
    	if (tenant == null)
    		return new ResponseEntity<ApplicantsAndTenants>(HttpStatus.UNAUTHORIZED);
    	return new ResponseEntity<ApplicantsAndTenants>(tenant,HttpStatus.OK);
       	
    }
    
    
   /* @GET
    @Path("{max}/{first}")
    @Produces({"application/xml", "application/json"})
    @Transactional
    public List<ApplicantsAndTenants> findRange(@PathParam("max") Integer max, @PathParam("first") Integer first) {
        return find(false, max, first);
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    @Transactional
    public String count() {
        try {
            Query query = entityManager.createQuery("SELECT count(o) FROM ApplicantsAndTenants AS o");
            return query.getSingleResult().toString();
        } finally {
            entityManager.close();
        }
    }

    private List<ApplicantsAndTenants> find(boolean all, int maxResults, int firstResult) {
        try {
            Query query = entityManager.createQuery("SELECT object(o) FROM ApplicantsAndTenants AS o");
            if (!all) {
                query.setMaxResults(maxResults);
                query.setFirstResult(firstResult);
            }
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }*/
    
}
