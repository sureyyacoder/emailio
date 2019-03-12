package com.tuncayuzun.emailio.repository;

import org.springframework.stereotype.Repository;

import com.tuncayuzun.emailio.model.WelcomeEmail;

@Repository
public interface WelcomeEmailRepository extends GenericEmailRepository<WelcomeEmail> {

}
