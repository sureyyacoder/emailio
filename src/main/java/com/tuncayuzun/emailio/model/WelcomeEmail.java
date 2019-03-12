package com.tuncayuzun.emailio.model;

import java.io.Serializable;

import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class WelcomeEmail implements Serializable {

	private static final long serialVersionUID = 1L;
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

}
