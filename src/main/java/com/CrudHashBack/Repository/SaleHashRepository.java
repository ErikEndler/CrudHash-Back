package com.CrudHashBack.Repository;

import org.springframework.data.repository.CrudRepository;

import com.CrudHashBack.Models.SaleHash;

public interface SaleHashRepository extends CrudRepository<SaleHash, Integer> {
	SaleHash findBySaleId(int id);

}
