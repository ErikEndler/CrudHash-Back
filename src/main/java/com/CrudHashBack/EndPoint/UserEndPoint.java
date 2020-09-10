package com.CrudHashBack.EndPoint;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CrudHashBack.Control.UserControl;
import com.CrudHashBack.DTO.UserDTO;
import com.CrudHashBack.Models.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/user")
@Api(value = "API REST User")
@CrossOrigin(origins = "*")
public class UserEndPoint {

	@Autowired
	UserControl userControl;

	@ApiOperation(value = "Salva um User")
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody UserDTO UserDTO) throws NoSuchAlgorithmException  {
		User user = userControl.salve(UserDTO);
		return new ResponseEntity<>(userResponse(user), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Edita um User")
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody UserDTO UserDTO) {
		User User = userControl.edit(UserDTO);
		return new ResponseEntity<>(userResponse(User), HttpStatus.ACCEPTED);
	}

	@ApiOperation(value = "Deleta um User por Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") int id) {
		userControl.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna uma lista de Usuarios")
	@GetMapping("")
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(listResponse(userControl.listarTodos()), HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna um Usuario unico pelo ID")
	@GetMapping("/{id}")
	public ResponseEntity<?> list(@PathVariable(value = "id") int id) {
		Optional<User> user = userControl.listar(id);
		return new ResponseEntity<>(userResponse(user.get()), HttpStatus.OK);
	}

	private UserDTO userResponse(User user) {
		return new UserDTO(user.getId(), user.getName(), user.getPassword());
	}

	// Recebe uma lista de usuarios e transforma a lista para o formato de resposta
	private Iterable<UserDTO> listResponse(Iterable<User> listaUsuarios) {
		// Cria a lista que sera retornada
		List<UserDTO> listDTO = new ArrayList<UserDTO>();
		// Faz um for na lista recebida no metodo
		for (User usuario : listaUsuarios) {
			listDTO.add(userResponse(usuario));
		}
		return listDTO;
	}

}
