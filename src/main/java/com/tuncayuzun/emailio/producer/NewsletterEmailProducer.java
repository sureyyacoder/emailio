package com.tuncayuzun.emailio.producer;

import java.text.MessageFormat;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuncayuzun.emailio.model.NewsletterEmail;
import com.tuncayuzun.emailio.service.EmailService;

@RestController
@RequestMapping(path = "api/emailio/")
public class NewsletterEmailProducer {

	@Autowired
	private EmailService emailService;

	@Autowired
	private Queue newsletterQueue;

	@Autowired
	private JmsTemplate jmsNewsletterTemplate;

	@PostMapping("newsletter")
	public NewsletterEmail newsletterSend(@RequestBody NewsletterEmail email) {

		email.setStatus("sending to queue");
		email.setSubject(MessageFormat.format("{0}, {1} tarihli bültenimizi kaçırma", email.getFirstName(),
				email.getNewsletterDate()));
		email.setBody(MessageFormat.format("Merhaba {0} {1}; {2} ", email.getFirstName(), email.getLastName(),
				email.getNewsletter()));
		emailService.saveNewsletterEmail(email);

		jmsNewsletterTemplate.convertAndSend(newsletterQueue, email);

		return email;

	}
}
