package org.huntsboro.admin.services;

import java.util.List;

import org.huntsboro.data.dao.EmploymentIncomeHistoryRepository;
import org.huntsboro.data.entity.EmploymentIncomeHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/employmentincomehistory")
@RestController
public class EmploymentIncomeHistoryResource {

		
		@Autowired
		private EmploymentIncomeHistoryRepository repo ;
		
	    public EmploymentIncomeHistoryResource() {
	    }

	    @RequestMapping(value="/",method=RequestMethod.POST,consumes={"application/xml", "application/json"})
	    @Transactional
	    public ResponseEntity<EmploymentIncomeHistory> create(@RequestBody EmploymentIncomeHistory entity,UriComponentsBuilder ucBuilder) {
	        repo.save(entity);
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/applicantsandtenant/{id}").buildAndExpand(entity.getId().toString()).toUri());
	        return new ResponseEntity<EmploymentIncomeHistory>(entity, HttpStatus.OK);
	    }

	    @RequestMapping(value="/",method=RequestMethod.PUT,consumes = {"application/xml", "application/json"})
	    @Transactional  
	    public ResponseEntity<EmploymentIncomeHistory> edit(EmploymentIncomeHistory entity) {
	    	EmploymentIncomeHistory  one = repo.findOne(entity.getId());
	    	if(one ==null){
	    		return new ResponseEntity<EmploymentIncomeHistory>(HttpStatus.NOT_FOUND);
	    	}
	    	repo.delete(one);
	        return new ResponseEntity<EmploymentIncomeHistory>(HttpStatus.NO_CONTENT);
	    }

	    @RequestMapping(value="/{id}",method=RequestMethod.DELETE,consumes = {"application/xml", "application/json"})
	    @Transactional
	    public ResponseEntity<EmploymentIncomeHistory> remove(@PathVariable("id") Integer id) {
	    	EmploymentIncomeHistory  entity = repo.findOne(id);
	        if(entity ==null){
	    		return new ResponseEntity<EmploymentIncomeHistory>(HttpStatus.NOT_FOUND);
	    	}
	        repo.delete(entity);
	        return new ResponseEntity<EmploymentIncomeHistory>(HttpStatus.NO_CONTENT);
	    }

	    
	    @RequestMapping(value="/{id}",method=RequestMethod.GET,produces = {"application/xml", "application/json"})
	    @Transactional
	    public ResponseEntity<EmploymentIncomeHistory> find(@PathVariable("id") Integer id) {
	    	EmploymentIncomeHistory one= repo.findOne(id);
	        if(one ==null)
	        	return new ResponseEntity<EmploymentIncomeHistory>(HttpStatus.NOT_FOUND);

	        return new ResponseEntity<EmploymentIncomeHistory>(one,HttpStatus.OK);
	    }

	    @RequestMapping(value="/",method=RequestMethod.GET, produces={"application/xml","application/json"} )
	    @Transactional
	    @ResponseBody
	    public ResponseEntity<List<EmploymentIncomeHistory>> findAll() {
	        List<EmploymentIncomeHistory> list = repo.findAll();
	        if(list.isEmpty())
	        	return new ResponseEntity<List<EmploymentIncomeHistory>>(HttpStatus.NO_CONTENT);
	        return new ResponseEntity<List<EmploymentIncomeHistory>>(list,HttpStatus.OK);
	    }

	  
	    
	}
