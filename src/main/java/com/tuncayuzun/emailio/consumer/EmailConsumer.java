package com.tuncayuzun.emailio.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.tuncayuzun.emailio.model.Email;

@Component
public class EmailConsumer {
	
	@Autowired
	private JavaMailSender javaMailSender;

    @JmsListener(destination = "welcome-password-queue")
    public void welcomeAndPasswordListener(Email email){
        System.out.println("Received Welcome Message; "+email);
        
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        
        simpleMailMessage.setTo("*****@gmail.com");
        simpleMailMessage.setSubject("Welcome");
        simpleMailMessage.setText("We are very pleased to have you join us...");
 
     
        javaMailSender.send(simpleMailMessage);
    }
    
    @JmsListener(destination = "newsletter-queue")
    public void newsletterlListener(String msg){
        System.out.println("Received Newsletter Message; "+msg);
    }
   
}