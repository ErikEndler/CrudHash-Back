package com.CrudHashBack.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SaleDTO {

	@JsonIgnore
	private int id;
	private String description;
	private Double value;
	@JsonIgnore
	private String hash;
	@JsonIgnore
	private boolean integridade;

}
