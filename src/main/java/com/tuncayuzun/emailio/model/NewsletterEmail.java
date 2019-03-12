package com.tuncayuzun.emailio.model;

import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
public class NewsletterEmail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String to;
	private String cc;
	private String subject;
	private String body;
	private String status;
	private String firstName;
	private String lastName;
	private Date newsletterDate;
	private String newsletter;

}
