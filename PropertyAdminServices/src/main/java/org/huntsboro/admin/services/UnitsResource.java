package org.huntsboro.admin.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.huntsboro.data.dao.UnitsRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/units")
@RestController
public class UnitsResource {

	@Autowired
	private UnitsRepository repo;

	public UnitsResource() {
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<Units> create(@RequestBody Units entity, UriComponentsBuilder ucBuilder) {
		repo.save(entity);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("/applicantsandtenant/{id}").buildAndExpand(entity.getId().toString()).toUri());
		return new ResponseEntity<Units>(entity, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<Units> edit(Units entity) {
		Units one = repo.findOne(entity.getId());
		if (one == null) {
			return new ResponseEntity<Units>(HttpStatus.NOT_FOUND);
		}
		repo.delete(one);
		return new ResponseEntity<Units>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = { "application/xml",
			"application/json" })
	@Transactional
	public ResponseEntity<Units> remove(@PathVariable("id") Integer id) {
		Units entity = repo.findOne(id);
		if (entity == null) {
			return new ResponseEntity<Units>(HttpStatus.NOT_FOUND);
		}
		repo.delete(entity);
		return new ResponseEntity<Units>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<Units> find(@PathVariable("id") Integer id) {
		Units one = repo.findOne(id);
		if (one == null)
			return new ResponseEntity<Units>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<Units>(one, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@Transactional
	@ResponseBody
	public ResponseEntity<List<Units>> findAll() {
		List<Units> list = repo.findAll();
		if (list.isEmpty())
			return new ResponseEntity<List<Units>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Units>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/available", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@Transactional
	@ResponseBody
	public ResponseEntity<List<Units>> getAvailableUnitsInAllProperties() {
		List<Units> list = repo.findByStatus("A");
		if (list.isEmpty())
			return new ResponseEntity<List<Units>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Units>>(list, HttpStatus.OK);
	}
	
	
	
}
