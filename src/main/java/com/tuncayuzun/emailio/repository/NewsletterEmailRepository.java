package com.tuncayuzun.emailio.repository;

import org.springframework.stereotype.Repository;

import com.tuncayuzun.emailio.model.NewsletterEmail;

@Repository
public interface NewsletterEmailRepository extends GenericEmailRepository<NewsletterEmail> {

}
