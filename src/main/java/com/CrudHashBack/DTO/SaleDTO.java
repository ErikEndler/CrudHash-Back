package com.CrudHashBack.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaleDTO {

	private int id;
	private String description;
	private Double value;
	@JsonProperty(access = Access.READ_ONLY)
	private String hash;
	@JsonProperty(access = Access.READ_ONLY)
	private boolean integridade;

	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public void setIntegridade(boolean integridade) {
		this.integridade = integridade;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Double getValue() {
		return value;
	}

	public String getHash() {
		return hash;
	}

	public boolean isIntegridade() {
		return integridade;
	}

}
