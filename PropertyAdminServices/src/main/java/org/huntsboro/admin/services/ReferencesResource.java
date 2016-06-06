package org.huntsboro.admin.services;

import java.util.List;

import org.huntsboro.data.dao.ReferencesRepository;
import org.huntsboro.data.entity.References;
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

@RequestMapping("/references")
@RestController
public class ReferencesResource {

		@Autowired
		private ReferencesRepository repo;

		public ReferencesResource() {
		}

		@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/xml", "application/json" })
		@Transactional
		public ResponseEntity<References> create(@RequestBody References entity, UriComponentsBuilder ucBuilder) {
			repo.save(entity);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(
					ucBuilder.path("/applicantsandtenant/{id}").buildAndExpand(entity.getId().toString()).toUri());
			return new ResponseEntity<References>(entity, HttpStatus.OK);
		}

		@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = { "application/xml", "application/json" })
		@Transactional
		public ResponseEntity<References> edit(References entity) {
			References one = repo.findOne(entity.getId());
			if (one == null) {
				return new ResponseEntity<References>(HttpStatus.NOT_FOUND);
			}
			repo.delete(one);
			return new ResponseEntity<References>(HttpStatus.NO_CONTENT);
		}

		@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = { "application/xml",
				"application/json" })
		@Transactional
		public ResponseEntity<References> remove(@PathVariable("id") Integer id) {
			References entity = repo.findOne(id);
			if (entity == null) {
				return new ResponseEntity<References>(HttpStatus.NOT_FOUND);
			}
			repo.delete(entity);
			return new ResponseEntity<References>(HttpStatus.NO_CONTENT);
		}

		@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
		@Transactional
		public ResponseEntity<References> find(@PathVariable("id") Integer id) {
			References one = repo.findOne(id);
			if (one == null)
				return new ResponseEntity<References>(HttpStatus.NOT_FOUND);

			return new ResponseEntity<References>(one, HttpStatus.OK);
		}

		@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
		@Transactional
		@ResponseBody
		public ResponseEntity<List<References>> findAll() {
			List<References> list = repo.findAll();
			if (list.isEmpty())
				return new ResponseEntity<List<References>>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<List<References>>(list, HttpStatus.OK);
		}

	}

