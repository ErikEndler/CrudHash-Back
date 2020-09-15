package com.CrudHashBack.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SaleDTO {

	
	private int id;
	private String description;
	private Double value;
	private String hash;
	private boolean integridade;

}
