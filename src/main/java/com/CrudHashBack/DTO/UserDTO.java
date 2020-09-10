package com.CrudHashBack.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

	private int id;
	private String name;
	private String password;

}
