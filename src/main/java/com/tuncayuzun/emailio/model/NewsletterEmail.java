package com.tuncayuzun.emailio.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class NewsletterEmail implements Serializable {

	private static final long serialVersionUID = 3L;
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
