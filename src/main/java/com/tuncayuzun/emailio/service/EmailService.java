package com.tuncayuzun.emailio.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuncayuzun.emailio.model.Email;
import com.tuncayuzun.emailio.repository.EmailRepository;

@Service
public class EmailService {
	
	private Logger LOG =  LoggerFactory.getLogger(Email.class);
	
	@Autowired
	EmailRepository emailRepository;
	
	
	public void saveEmail(Email email) {
		try {
			emailRepository.save(email);
		} catch (Exception e) {
			LOG.error("An error occured during email saving : "+e.getMessage());
		}
		
	}

}
