package com.tru.surveyrest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tru.surveyrest.jpa.User;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByRole(String string);

}
