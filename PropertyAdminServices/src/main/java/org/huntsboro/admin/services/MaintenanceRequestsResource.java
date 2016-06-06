package org.huntsboro.admin.services;

import java.util.List;

import org.huntsboro.data.dao.MaintenanceRequestsRepository;
import org.huntsboro.data.entity.MaintenanceRequests;
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

@RequestMapping("/maintenancerequests")
@RestController
public class MaintenanceRequestsResource {

	@Autowired
	private MaintenanceRequestsRepository maintRepo;

	
	public MaintenanceRequestsResource() {
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<MaintenanceRequests> create(@RequestBody MaintenanceRequests entity, UriComponentsBuilder ucBuilder) {
		try {
			maintRepo.save(entity);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(
					ucBuilder.path("/maintenancerequests/{id}").buildAndExpand(entity.getRequestId().toString()).toUri());
			return new ResponseEntity<MaintenanceRequests>(entity, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<MaintenanceRequests>(entity, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<MaintenanceRequests> edit(MaintenanceRequests entity) {
		MaintenanceRequests one = maintRepo.findOne(entity.getRequestId());
		if (one == null) {
			return new ResponseEntity<MaintenanceRequests>(HttpStatus.NOT_FOUND);
		}
		maintRepo.delete(one);
		return new ResponseEntity<MaintenanceRequests>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = { "application/xml",
			"application/json" })
	@Transactional
	public ResponseEntity<MaintenanceRequests> remove(@PathVariable("id") Integer id) {
		MaintenanceRequests entity = maintRepo.findOne(id);
		if (entity == null) {
			return new ResponseEntity<MaintenanceRequests>(HttpStatus.NOT_FOUND);
		}
		maintRepo.delete(entity);
		return new ResponseEntity<MaintenanceRequests>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<MaintenanceRequests> find(@PathVariable("id") Integer id) {
		MaintenanceRequests one = maintRepo.findOne(id);
		if (one == null)
			return new ResponseEntity<MaintenanceRequests>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<MaintenanceRequests>(one, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@Transactional
	@ResponseBody
	public ResponseEntity<List<MaintenanceRequests>> findAll() {
		List<MaintenanceRequests> list = maintRepo.findAll();
		if (list.isEmpty())
			return new ResponseEntity<List<MaintenanceRequests>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<MaintenanceRequests>>(list, HttpStatus.OK);
	}

	

	
}
