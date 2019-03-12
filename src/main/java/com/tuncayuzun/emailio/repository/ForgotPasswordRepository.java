package com.tuncayuzun.emailio.repository;

import org.springframework.stereotype.Repository;

import com.tuncayuzun.emailio.model.ForgotPasswordEmail;

@Repository
public interface ForgotPasswordRepository extends GenericEmailRepository<ForgotPasswordEmail> {

}
