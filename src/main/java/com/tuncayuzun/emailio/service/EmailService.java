package com.tuncayuzun.emailio.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuncayuzun.emailio.model.ForgotPasswordEmail;
import com.tuncayuzun.emailio.model.NewsletterEmail;
import com.tuncayuzun.emailio.model.WelcomeEmail;
import com.tuncayuzun.emailio.repository.ForgotPasswordRepository;
import com.tuncayuzun.emailio.repository.NewsletterEmailRepository;
import com.tuncayuzun.emailio.repository.WelcomeEmailRepository;

@Service
public class EmailService {

	private Logger LOG = LoggerFactory.getLogger(WelcomeEmail.class);

	@Autowired
	WelcomeEmailRepository welcomeEmailRepository;
	
	@Autowired
	ForgotPasswordRepository forgotPasswordRepository;
	
	@Autowired
	NewsletterEmailRepository newsletterEmailRepository;

	public void saveWelcomeEmail(WelcomeEmail email) {
		try {
			welcomeEmailRepository.save(email);
		} catch (Exception e) {
			LOG.error("An error occured during welcome email saving : " + e.getMessage());
		}

	}
	
	public void savePasswordEmail(ForgotPasswordEmail email) {
		try {
			forgotPasswordRepository.save(email);
		} catch (Exception e) {
			LOG.error("An error occured during forgot password email saving : " + e.getMessage());
		}

	}
	
	public void saveNewsletterEmail(NewsletterEmail email) {
		try {
			newsletterEmailRepository.save(email);
		} catch (Exception e) {
			LOG.error("An error occured during newsletter email saving : " + e.getMessage());
		}

	}

}
