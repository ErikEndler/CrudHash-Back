package com.CrudHashBack.Repository;

import org.springframework.data.repository.CrudRepository;

import com.CrudHashBack.Models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
