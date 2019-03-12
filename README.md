## Emailio - Email Sender App

- Spring Boot
- JDK 1.8
- Maven
- ActiveMQ
- H2

## Installation Steps

1- Install [ActiveMQ](http://activemq.apache.org/download.html)  or run 
				
			 docker run -p 61616:61616 -p 8161:8161 webcenter/activemq:latest
			 
2- Configure **application.properties** file (for Gmail)
								
	spring.mail.host=smtp.gmail.com
	spring.mail.username=<yourmail>
	spring.mail.password=<your password>

Note: [Allow to less secure app for Gmail](https://myaccount.google.com/lesssecureapps) 		

3-  cd /path_to_project
			mvn spring-boot:run
			   
## Urls

ActiveMQ Dashboard : http://localhost:8181/admin/
H2 DB : http://localhost:8080/h2
