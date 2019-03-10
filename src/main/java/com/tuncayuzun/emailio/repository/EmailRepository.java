package com.tuncayuzun.emailio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tuncayuzun.emailio.model.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, String>{

}
