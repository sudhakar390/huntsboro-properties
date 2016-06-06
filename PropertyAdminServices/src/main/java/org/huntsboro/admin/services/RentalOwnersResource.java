package org.huntsboro.admin.services;

import java.util.List;

import org.huntsboro.data.dao.RentalOwnersRepository;
import org.huntsboro.data.entity.RentalOwners;
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

@RequestMapping("/rentalowners")
@RestController
public class RentalOwnersResource {

	@Autowired
	private RentalOwnersRepository repo;

	public RentalOwnersResource() {
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<RentalOwners> create(@RequestBody RentalOwners entity, UriComponentsBuilder ucBuilder) {
		repo.save(entity);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("/applicantsandtenant/{id}").buildAndExpand(entity.getId().toString()).toUri());
		return new ResponseEntity<RentalOwners>(entity, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<RentalOwners> edit(RentalOwners entity) {
		RentalOwners one = repo.findOne(entity.getId());
		if (one == null) {
			return new ResponseEntity<RentalOwners>(HttpStatus.NOT_FOUND);
		}
		repo.delete(one);
		return new ResponseEntity<RentalOwners>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = { "application/xml",
			"application/json" })
	@Transactional
	public ResponseEntity<RentalOwners> remove(@PathVariable("id") Integer id) {
		RentalOwners entity = repo.findOne(id);
		if (entity == null) {
			return new ResponseEntity<RentalOwners>(HttpStatus.NOT_FOUND);
		}
		repo.delete(entity);
		return new ResponseEntity<RentalOwners>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<RentalOwners> find(@PathVariable("id") Integer id) {
		RentalOwners one = repo.findOne(id);
		if (one == null)
			return new ResponseEntity<RentalOwners>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<RentalOwners>(one, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@Transactional
	@ResponseBody
	public ResponseEntity<List<RentalOwners>> findAll() {
		List<RentalOwners> list = repo.findAll();
		if (list.isEmpty())
			return new ResponseEntity<List<RentalOwners>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<RentalOwners>>(list, HttpStatus.OK);
	}

}
