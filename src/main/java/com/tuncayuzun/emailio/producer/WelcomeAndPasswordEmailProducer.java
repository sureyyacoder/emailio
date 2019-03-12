package com.tuncayuzun.emailio.producer;

import java.text.MessageFormat;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuncayuzun.emailio.model.ForgotPasswordEmail;
import com.tuncayuzun.emailio.model.WelcomeEmail;
import com.tuncayuzun.emailio.service.EmailService;
import com.tuncayuzun.emailio.utility.Helper;

@RestController
@RequestMapping(path = "api/emailio/")
public class WelcomeAndPasswordEmailProducer {

	@Autowired
	private EmailService emailService;

	@Autowired
	private Queue welcomeAndPasswordQueue;

	@Autowired
	private JmsTemplate jmsWelcomeTemplate, jmsPasswordTemplate;

	@PostMapping("welcome")
	public WelcomeEmail welcomeSend(@RequestBody WelcomeEmail email) {

		email.setStatus("sending to queue");
		email.setSubject("Hoşgeldiniz!");
		email.setBody(
				MessageFormat.format("Sayın {0} {1}, aramıza hoşgeldiniz.", email.getFirstName(), email.getLastName()));
		emailService.saveWelcomeEmail(email);

		jmsWelcomeTemplate.convertAndSend(welcomeAndPasswordQueue, email);

		return email;
	}

	@PostMapping("password")
	public ForgotPasswordEmail forgotPasswordSend(@RequestBody ForgotPasswordEmail email) {

		email.setStatus("sending to queue");
		email.setSubject("Şifre yenileme");
		email.setBody(MessageFormat.format("Şifrenizi yenilemek için aşağıdaki linke tıklayınız. {0}",
				Helper.urlGenerator(email.getPasswordUrl())));
		emailService.savePasswordEmail(email);

		jmsPasswordTemplate.convertAndSend(welcomeAndPasswordQueue, email);

		return email;

	}

}
