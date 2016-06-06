package org.huntsboro.admin.services;

import java.util.List;

import org.huntsboro.data.dao.PropertiesRepository;
import org.huntsboro.data.dao.UnitsRepository;
import org.huntsboro.data.entity.Properties;
import org.huntsboro.data.entity.Units;
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

@RequestMapping("/properties")
@RestController
public class PropertiesResource {

	@Autowired
	private PropertiesRepository repo;

	@Autowired
	private UnitsRepository unitRepo;

	
	public PropertiesResource() {
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<Properties> create(@RequestBody Properties entity, UriComponentsBuilder ucBuilder) {
		repo.save(entity);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("/properties/{id}").buildAndExpand(entity.getId().toString()).toUri());
		return new ResponseEntity<Properties>(entity, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<Properties> edit(Properties entity) {
		Properties one = repo.findOne(entity.getId());
		if (one == null) {
			return new ResponseEntity<Properties>(HttpStatus.NOT_FOUND);
		}
		repo.delete(one);
		return new ResponseEntity<Properties>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = { "application/xml",
			"application/json" })
	@Transactional
	public ResponseEntity<Properties> remove(@PathVariable("id") Integer id) {
		Properties entity = repo.findOne(id);
		if (entity == null) {
			return new ResponseEntity<Properties>(HttpStatus.NOT_FOUND);
		}
		repo.delete(entity);
		return new ResponseEntity<Properties>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<Properties> find(@PathVariable("id") Integer id) {
		Properties one = repo.findOne(id);
		if (one == null)
			return new ResponseEntity<Properties>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Properties>(one, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@Transactional
	@ResponseBody
	public ResponseEntity<List<Properties>> findAll() {
		List<Properties> list = repo.findAll();
		if (list.isEmpty())
			return new ResponseEntity<List<Properties>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Properties>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/units/A", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@Transactional
	@ResponseBody
	public ResponseEntity<List<Units>> getAvailableUnitsByProperty(@PathVariable("id") Integer id) {
		
		List<Units> list = unitRepo.findByPropertyIdAndStatus(id,"A");  // A - available, O - occupied
		if (list.isEmpty())
			return new ResponseEntity<List<Units>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Units>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/units/O", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@Transactional
	@ResponseBody
	public ResponseEntity<List<Units>> getOccupiedUnitsByProperty(@PathVariable("id") Integer id) {
		
		List<Units> list = unitRepo.findByPropertyIdAndStatus(id,"O");  // A - available, O - occupied
		if (list.isEmpty())
			return new ResponseEntity<List<Units>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Units>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/units", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@Transactional
	@ResponseBody
	public ResponseEntity<List<Units>> getAvailableUnitsByPropertyId(@PathVariable("id") Integer id) {
		
		List<Units> list = unitRepo.findByPropertyId(id);  
		if (list.isEmpty())
			return new ResponseEntity<List<Units>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Units>>(list, HttpStatus.OK);
	}
}
