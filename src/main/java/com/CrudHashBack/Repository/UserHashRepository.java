package com.CrudHashBack.Repository;

import org.springframework.data.repository.CrudRepository;

import com.CrudHashBack.Models.UserHash;

public interface UserHashRepository extends CrudRepository<UserHash, Integer> {
	UserHash findByUserId(int id);

}
