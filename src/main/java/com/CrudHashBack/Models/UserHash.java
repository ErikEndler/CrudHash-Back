package com.CrudHashBack.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserHash {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String hash;
	@OneToOne
	private User user;
	
	public UserHash(String hash, User user) {
		super();
		this.hash = hash;
		this.user = user;
	}
	
	
	
	

}
