package org.huntsboro.admin.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.huntsboro.data.dao.ApplicantsAndTenantsRepository;
import org.huntsboro.data.dao.ApplicationsAndLeasesRepository;
import org.huntsboro.data.dao.UnitsRepository;
import org.huntsboro.data.entity.ApplicantsAndTenants;
import org.huntsboro.data.entity.ApplicationsAndLeases;
import org.huntsboro.data.entity.Units;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/applicationsandleases")
@RestController
public class ApplicationsAndLeasesResource {
	
	@Autowired
	private ApplicationsAndLeasesRepository repo ;
	@Autowired
	private ApplicantsAndTenantsRepository tenantRepo ;
	@Autowired
	private UnitsRepository unitRepo ;
	
	
    public ApplicationsAndLeasesResource() {
    }

    @RequestMapping(value="/",method=RequestMethod.POST,consumes={"application/xml", "application/json"})
    @Transactional
    public ResponseEntity<ApplicationsAndLeases> create(@RequestBody ApplicationsAndLeases entity,UriComponentsBuilder ucBuilder) {
        repo.save(entity);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/applicantsandtenant/{id}").buildAndExpand(entity.getId().toString()).toUri());
        return new ResponseEntity<ApplicationsAndLeases>(entity, HttpStatus.OK);
    }

    @RequestMapping(value="/",method=RequestMethod.PUT,consumes = {"application/xml", "application/json"})
    @Transactional  
    public ResponseEntity<ApplicationsAndLeases> edit(ApplicationsAndLeases entity) {
    	ApplicationsAndLeases  one = repo.findOne(entity.getId());
    	if(one ==null){
    		return new ResponseEntity<ApplicationsAndLeases>(HttpStatus.NOT_FOUND);
    	}
    	repo.delete(one);
        return new ResponseEntity<ApplicationsAndLeases>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}",method=RequestMethod.DELETE,consumes = {"application/xml", "application/json"})
    @Transactional
    public ResponseEntity<ApplicationsAndLeases> remove(@PathVariable("id") Integer id) {
    	ApplicationsAndLeases  entity = repo.findOne(id);
        if(entity ==null){
    		return new ResponseEntity<ApplicationsAndLeases>(HttpStatus.NOT_FOUND);
    	}
        repo.delete(entity);
        return new ResponseEntity<ApplicationsAndLeases>(HttpStatus.NO_CONTENT);
    }

    
    @RequestMapping(value="/{id}",method=RequestMethod.GET,produces = {"application/xml", "application/json"})
    @Transactional
    public ResponseEntity<ApplicationsAndLeases> find(@PathVariable("id") Integer id) {
    	ApplicationsAndLeases one= repo.findOne(id);
        if(one ==null)
        	return new ResponseEntity<ApplicationsAndLeases>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<ApplicationsAndLeases>(one,HttpStatus.OK);
    }

    @RequestMapping(value="/",method=RequestMethod.GET, produces={"application/xml","application/json"} )
    @Transactional
    @ResponseBody
    public ResponseEntity<List<ApplicationsAndLeases>> findAll() {
        List<ApplicationsAndLeases> list = repo.findAll();
        if(list.isEmpty())
        	return new ResponseEntity<List<ApplicationsAndLeases>>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<ApplicationsAndLeases>>(list,HttpStatus.OK);
    }
    
    @RequestMapping(value="/Properties/{p_id}/Units/{u_id}",method=RequestMethod.GET,produces = {"application/xml", "application/json"})
    @Transactional
    public ResponseEntity<List<ApplicantsAndTenants>> getTenantsByPropertyAndUnit(@PathVariable("p_id") Integer propertyId, @PathVariable("u_id") Integer unitId) {
    	List<ApplicationsAndLeases> leases = repo.findByPropertyAndUnit(propertyId, unitId);
        if(CollectionUtils.isEmpty(leases))
        	return new ResponseEntity<List<ApplicantsAndTenants>>(HttpStatus.NOT_FOUND);
        List<ApplicantsAndTenants> list = new ArrayList<ApplicantsAndTenants>();
        for(ApplicationsAndLeases lease : leases)
        {
        	Integer tenantId = lease.getTenants();
        	ApplicantsAndTenants tenant = tenantRepo.findOne(tenantId);
        	list.add(tenant);
        }
        return new ResponseEntity<List<ApplicantsAndTenants>>(list,HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@Transactional
	@ResponseBody
	public ResponseEntity<List<Units>> searchAvailableUnits(@RequestParam ("moveInDate") String moveInDate , @RequestParam ("beds") String beds ,@RequestParam ("baths") String baths) {
		
		Date searchDate = toDate(moveInDate);
		List<ApplicationsAndLeases> availableProperties = repo.findByEndDateBefore(searchDate);
		if(availableProperties.isEmpty())
			return new ResponseEntity<List<Units>>(HttpStatus.NO_CONTENT); 
		Set<Units> availableUnits = new HashSet<Units>();
		
		for(ApplicationsAndLeases p : availableProperties)
		{
			List<Units> list = null;
			if(Integer.parseInt(beds) > 0 && Integer.parseInt(baths) < 0 )
				list = unitRepo.findByPropertyIdAndUnitNumberAndRoomsAndStatus(p.getProperty(), p.getUnit(), Integer.parseInt(beds),"A");
			else if(Integer.parseInt(beds) < 0 && Integer.parseInt(baths) > 0 )
				list = unitRepo.findByPropertyIdAndUnitNumberAndBathroomAndStatus(p.getProperty(), p.getUnit(), Integer.parseInt(baths),"A");
			else if(Integer.parseInt(beds) > 0 && Integer.parseInt(baths) > 0 )
				list = unitRepo.findByPropertyIdAndUnitNumberAndRoomsAndBathroomAndStatus(p.getProperty(), p.getUnit(), Integer.parseInt(beds), Integer.parseInt(baths),"A");
			else if(Integer.parseInt(beds) < 0 && Integer.parseInt(baths) < 0 )
				list = unitRepo.findByPropertyIdAndUnitNumberAndStatus(p.getProperty(), p.getUnit(), "A");

			availableUnits.addAll(list);
		}
		
		if (availableUnits.isEmpty())
			return new ResponseEntity<List<Units>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Units>>(new ArrayList<Units>(availableUnits), HttpStatus.OK);
	}
	
	private Date toDate(String dateStr){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		try {
			return formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
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
