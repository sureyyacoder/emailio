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
			LOG.error("An error occured: " + e.getMessage());
		}
	}

	public void updateWelcomeEmailStatus(WelcomeEmail email) {
		try {
			WelcomeEmail forUpdate = welcomeEmailRepository.findById(email.getId()).orElse(new WelcomeEmail());
			forUpdate.setStatus("failed");
			welcomeEmailRepository.save(forUpdate);
			LOG.info("Welcome Email status changed to fail!");
		} catch (Exception e) {
			LOG.error("An error occured: " + e.getMessage());
		}
	}

	public void savePasswordEmail(ForgotPasswordEmail email) {
		try {
			forgotPasswordRepository.save(email);
		} catch (Exception e) {
			LOG.error("An error occured: " + e.getMessage());
		}

	}

	public void updatePasswordEmailStatus(ForgotPasswordEmail email) {
		try {
			ForgotPasswordEmail forUpdate = forgotPasswordRepository.findById(email.getId())
					.orElse(new ForgotPasswordEmail());
			forUpdate.setStatus("failed");
			forgotPasswordRepository.save(forUpdate);
			LOG.info("Forgot Password Email status changed to fail!");
		} catch (Exception e) {
			LOG.error("An error occured: " + e.getMessage());
		}
	}

	public void saveNewsletterEmail(NewsletterEmail email) {
		try {
			newsletterEmailRepository.save(email);
		} catch (Exception e) {
			LOG.error("An error occured: " + e.getMessage());
		}

	}

	public void updateNewsletterEmailStatus(NewsletterEmail email) {
		try {
			NewsletterEmail forUpdate = newsletterEmailRepository.findById(email.getId()).orElse(new NewsletterEmail());
			forUpdate.setStatus("failed");
			newsletterEmailRepository.save(forUpdate);
			LOG.info("Newsletter Email status changed to fail!");
		} catch (Exception e) {
			LOG.error("An error occured: " + e.getMessage());
		}
	}

}
