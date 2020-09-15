package com.CrudHashBack.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor

public class SaleHash {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String hash;
	@OneToOne
	private Sale sale;
	
	public SaleHash(String hash, Sale sale) {
		super();
		this.hash = hash;
		this.sale = sale;
	}
	
	
	
	

}
