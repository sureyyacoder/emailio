package com.tuncayuzun.emailio.producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuncayuzun.emailio.model.Email;
import com.tuncayuzun.emailio.service.EmailService;

import javax.jms.Queue;

@RestController
@RequestMapping(path="api/emailio/")
public class WelcomeAndPasswordEmailProducer {

	
	@Autowired
	private EmailService emailService;

	@Autowired
    private Queue welcomeAndPasswordQueue;

    @Autowired
    private JmsTemplate jmsTemplate;


    @PostMapping("welcome")
    public void welcome(@RequestBody Email email ){
    	
        jmsTemplate.convertAndSend(welcomeAndPasswordQueue, email);
        emailService.saveEmail(email);

    }


}
