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

import com.CrudHashBack.Control.SaleControl;
import com.CrudHashBack.Control.SaleHashControl;
import com.CrudHashBack.DTO.SaleDTO;
import com.CrudHashBack.Models.Sale;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/sale")
@Api(value = "API REST Sales")
@CrossOrigin(origins = "*")
public class SaleEndPoint {

	@Autowired
	SaleControl saleControl;
	@Autowired
	SaleHashControl saleHashControl;

	@ApiOperation(value = "Save one Sale")
	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody SaleDTO saleDTO) throws NoSuchAlgorithmException  {
		Sale user = saleControl.salve(saleDTO);
		return new ResponseEntity<>(saleResponse(user), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Edit one Sale")
	@PutMapping("")
	public ResponseEntity<?> update(@RequestBody SaleDTO saleDTO) {
		Sale User = saleControl.edit(saleDTO);
		return new ResponseEntity<>(saleResponse(User), HttpStatus.ACCEPTED);
	}

	@ApiOperation(value = "Delete one sale")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") int id) {
		saleControl.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "Returns a sales list")
	@GetMapping("")
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(listResponse(saleControl.listAll()), HttpStatus.OK);
	}

	@ApiOperation(value = "Returns a sale by ID")
	@GetMapping("/{id}")
	public ResponseEntity<?> list(@PathVariable(value = "id") int id) {
		Optional<Sale> user = saleControl.list(id);
		return new ResponseEntity<>(saleResponse(user.get()), HttpStatus.OK);
	}

	private SaleDTO saleResponse(Sale sale) {
		
		return new SaleDTO(sale.getId(), sale.getDescription(), sale.getValue(), saleHashControl.listIdUser(sale.getId()).getHash(),saleControl.compare(sale.getId()));
	}

	// Recebe uma lista de sales e transforma a lista para o formato de resposta
	private Iterable<SaleDTO> listResponse(Iterable<Sale> listSales) {
		// Cria a lista que sera retornada
		List<SaleDTO> listDTO = new ArrayList<SaleDTO>();
		// Faz um for na lista recebida no metodo
		for (Sale sale : listSales) {
			listDTO.add(saleResponse(sale));
		}
		return listDTO;
	}

}
