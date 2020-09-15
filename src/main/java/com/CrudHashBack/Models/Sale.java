package com.CrudHashBack.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor


public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private Double value;
	@OneToOne(mappedBy = "sale")
	private SaleHash hash;

	public Sale(int id, String description, Double value) {
		super();
		this.id = id;
		this.description = description;
		this.value = value;
	}

	public Sale(String description, Double value) {
		super();
		this.description = description;
		this.value = value;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", description=" + description + ", value=" + value + ", hash=" + hash.getHash() + "]";
	}
	

}
