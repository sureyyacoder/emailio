package com.tuncayuzun.emailio.producer;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.tuncayuzun.emailio.model.Email;

@RestController
public class NewsletterEmailProducer {

	@Autowired
	private Queue newsletterQueue;

	@Autowired
	private JmsTemplate jmsTemplate;

	@GetMapping("newsletter/{email}")
	public String welcome(@PathVariable("msg") Email email) {

		jmsTemplate.convertAndSend(newsletterQueue, email);
		return "NewsLetter:  <b>" + email + "</b>";

	}
}
