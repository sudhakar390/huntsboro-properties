package org.huntsboro.admin.services;

import org.huntsboro.admin.dto.EmailDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/email")
@RestController
public class EmailerResource {

	public EmailerResource() {
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = { "application/xml", "application/json" })
	@Transactional
	public ResponseEntity<EmailDTO> sendEmail(@RequestBody EmailDTO dto, UriComponentsBuilder ucBuilder) {
		try {
			Emailer.sendMail(dto.getMailTo(), dto.getMailFrom(), dto.getMsgText(), dto.getSubject());
			return new ResponseEntity<EmailDTO>(dto, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<EmailDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
}
