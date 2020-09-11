package com.CrudHashBack.Control;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CrudHashBack.Models.User;
import com.CrudHashBack.Models.UserHash;
import com.CrudHashBack.Repository.UserHashRepository;
import com.CrudHashBack.Util.Encryption;

@Service
public class UserHashControl {

	Encryption encryption = new Encryption();
	@Autowired
	UserHashRepository userHashRepository;

	public String encrypt(User user) throws NoSuchAlgorithmException  {
		String string = user.getName() + user.getPassword();
		System.out.println("STRING :"+string);
		String result = encryption.encrypter(string);
		return result;
	}

	public User save(User user) throws NoSuchAlgorithmException  {
		String hash = encrypt(user);
		userHashRepository.save(new UserHash(hash, user));
		return user;
	}
	public UserHash listIdUser(int id) {
		return userHashRepository.findByUserId(id);
		
	}

	public void list() {

	}

	public void delete(int id) {
		UserHash userhash =listIdUser(id);
		userHashRepository.deleteById(userhash.getId());
		
	}

}
