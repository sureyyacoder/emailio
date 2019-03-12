package com.tuncayuzun.emailio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;





@NoRepositoryBean
public interface GenericEmailRepository<T> extends JpaRepository<T, Long>{

}
