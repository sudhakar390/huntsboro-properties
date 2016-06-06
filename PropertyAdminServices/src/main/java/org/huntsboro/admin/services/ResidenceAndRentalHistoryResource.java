package org.huntsboro.admin.services;

import java.util.List;

import org.huntsboro.data.dao.ResidenceAndRentalHistoryRepository;
import org.huntsboro.data.entity.ResidenceAndRentalHistory;
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

@RequestMapping("/residenceandrentalhistory")
@RestController
public class ResidenceAndRentalHistoryResource {

		@Autowired
		private ResidenceAndRentalHistoryRepository repo;

		public ResidenceAndRentalHistoryResource() {
		}

		@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/xml", "application/json" })
		@Transactional
		public ResponseEntity<ResidenceAndRentalHistory> create(@RequestBody ResidenceAndRentalHistory entity, UriComponentsBuilder ucBuilder) {
			repo.save(entity);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(
					ucBuilder.path("/applicantsandtenant/{id}").buildAndExpand(entity.getId().toString()).toUri());
			return new ResponseEntity<ResidenceAndRentalHistory>(entity, HttpStatus.OK);
		}

		@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = { "application/xml", "application/json" })
		@Transactional
		public ResponseEntity<ResidenceAndRentalHistory> edit(ResidenceAndRentalHistory entity) {
			ResidenceAndRentalHistory one = repo.findOne(entity.getId());
			if (one == null) {
				return new ResponseEntity<ResidenceAndRentalHistory>(HttpStatus.NOT_FOUND);
			}
			repo.delete(one);
			return new ResponseEntity<ResidenceAndRentalHistory>(HttpStatus.NO_CONTENT);
		}

		@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = { "application/xml",
				"application/json" })
		@Transactional
		public ResponseEntity<ResidenceAndRentalHistory> remove(@PathVariable("id") Integer id) {
			ResidenceAndRentalHistory entity = repo.findOne(id);
			if (entity == null) {
				return new ResponseEntity<ResidenceAndRentalHistory>(HttpStatus.NOT_FOUND);
			}
			repo.delete(entity);
			return new ResponseEntity<ResidenceAndRentalHistory>(HttpStatus.NO_CONTENT);
		}

		@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
		@Transactional
		public ResponseEntity<ResidenceAndRentalHistory> find(@PathVariable("id") Integer id) {
			ResidenceAndRentalHistory one = repo.findOne(id);
			if (one == null)
				return new ResponseEntity<ResidenceAndRentalHistory>(HttpStatus.NOT_FOUND);

			return new ResponseEntity<ResidenceAndRentalHistory>(one, HttpStatus.OK);
		}

		@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/xml", "application/json" })
		@Transactional
		@ResponseBody
		public ResponseEntity<List<ResidenceAndRentalHistory>> findAll() {
			List<ResidenceAndRentalHistory> list = repo.findAll();
			if (list.isEmpty())
				return new ResponseEntity<List<ResidenceAndRentalHistory>>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<List<ResidenceAndRentalHistory>>(list, HttpStatus.OK);
		}

	}

