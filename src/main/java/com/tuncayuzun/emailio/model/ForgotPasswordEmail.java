package com.tuncayuzun.emailio.model;

import javax.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
public class ForgotPasswordEmail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String to;
	private String cc;
	private String subject;
	private String body;
	private String status;
	private String passwordUrl;

}
