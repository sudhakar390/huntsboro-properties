package org.huntsboro.admin.services;

import java.util.List;

import org.huntsboro.data.dao.BroadcastMessagesRepository;
import org.huntsboro.data.entity.BroadcastMessages;
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

@RequestMapping("/tenantmessages")
@RestController
public class BroadcastMessagesResource {

	@Autowired
	private BroadcastMessagesRepository msgRepo;

	
	public BroadcastMessagesResource() {
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<BroadcastMessages> create(@RequestBody BroadcastMessages entity, UriComponentsBuilder ucBuilder) {
		try {
			msgRepo.save(entity);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(
					ucBuilder.path("/tenantmessages/{id}").buildAndExpand(entity.getMsgId().toString()).toUri());
			return new ResponseEntity<BroadcastMessages>(entity, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<BroadcastMessages>(entity, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<BroadcastMessages> edit(BroadcastMessages entity) {
		BroadcastMessages one = msgRepo.findOne(entity.getMsgId());
		if (one == null) {
			return new ResponseEntity<BroadcastMessages>(HttpStatus.NOT_FOUND);
		}
		msgRepo.delete(one);
		return new ResponseEntity<BroadcastMessages>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = { "application/xml",
			"application/json" })
	@Transactional
	public ResponseEntity<BroadcastMessages> remove(@PathVariable("id") Integer id) {
		BroadcastMessages entity = msgRepo.findOne(id);
		if (entity == null) {
			return new ResponseEntity<BroadcastMessages>(HttpStatus.NOT_FOUND);
		}
		msgRepo.delete(entity);
		return new ResponseEntity<BroadcastMessages>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<BroadcastMessages> find(@PathVariable("id") Integer id) {
		BroadcastMessages one = msgRepo.findOne(id);
		if (one == null)
			return new ResponseEntity<BroadcastMessages>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<BroadcastMessages>(one, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
	@Transactional
	@ResponseBody
	public ResponseEntity<List<BroadcastMessages>> findAll() {
		List<BroadcastMessages> list = msgRepo.findAll();
		if (list.isEmpty())
			return new ResponseEntity<List<BroadcastMessages>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<BroadcastMessages>>(list, HttpStatus.OK);
	}

	

	
}
