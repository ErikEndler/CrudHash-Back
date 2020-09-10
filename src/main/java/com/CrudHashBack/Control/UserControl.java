package com.CrudHashBack.Control;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CrudHashBack.DTO.UserDTO;
import com.CrudHashBack.Models.User;
import com.CrudHashBack.Repository.UserRepository;
import com.CrudHashBack.Util.ResourceNotFoundException;

@Service
public class UserControl {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserHashControl userHashControl;

	public void deleteById(int id) {
		verifyExist(id);
		userRepository.deleteById(id);
	}

	public User edit(UserDTO userDTO) {
		verifyExist(userDTO.getId());
		User user = transformEdit(userDTO);
		return userRepository.save(user);
	}

	public User salve(UserDTO userDTO) throws NoSuchAlgorithmException {
		User user = transformSave(userDTO);
		return userHashControl.save(userRepository.save(user));
	}

	public Iterable<User> listarTodos() {
		return userRepository.findAll();
	}

	public Optional<User> listar(int id) {
		return userRepository.findById(id);
	}

	// ------------------------------------------------------
	private void verifyExist(int id) {
		Optional<User> retorno = userRepository.findById(id);
		retorno.orElseThrow(() -> new ResourceNotFoundException(MenssagemErro() + " not found to ID: " + id));

	}

	private String MenssagemErro() {
		String msg = "User";
		return msg;
	}

	private User transformEdit(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getPassword());
	}

	private User transformSave(UserDTO userDTO) {
		return new User(userDTO.getName(), userDTO.getPassword());
	}

}
