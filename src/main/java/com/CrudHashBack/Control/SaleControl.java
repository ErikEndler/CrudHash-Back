package com.CrudHashBack.Control;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CrudHashBack.DTO.SaleDTO;
import com.CrudHashBack.Models.Sale;
import com.CrudHashBack.Repository.SaleRepository;
import com.CrudHashBack.Util.ResourceNotFoundException;
import com.CrudHashBack.Util.WriteLog;

@Service
public class SaleControl {

	@Autowired
	SaleRepository saleRepository;
	@Autowired
	SaleHashControl saleHashControl;
	
	WriteLog writeLog = new WriteLog();

	public void deleteById(int id) {
		Sale sale = verifyExist(id);
		saleHashControl.delete(id);
		saleRepository.deleteById(id);
		writeLog.writer(sale.toString(),"delete");
	}

	public Sale edit(SaleDTO saleDTO) {
		String before = verifyExist(saleDTO.getId()).toString();
		System.out.println("ANTES DE EDITAR: " + before);
		Sale sale = transformEdit(saleDTO);
		Sale after = saleHashControl.edit(saleRepository.save(sale));
		writeLog.writer("Old -"+before+"- New -"+after.toString(),"edit");
		return after;
	}

	public Sale salve(SaleDTO saleDTO) throws NoSuchAlgorithmException {
		Sale user = transformSave(saleDTO);		
		Sale returnUser = saleHashControl.save(saleRepository.save(user));
		writeLog.writer(returnUser.toString(),"save");
		return returnUser;
	}

	public Iterable<Sale> listAll() {
		return saleRepository.findAll();
	}

	public Optional<Sale> list(int id) {
		return saleRepository.findById(id);
	}
	
	public boolean compare(int id)  {
		Sale user = list(id).get();
		String hash = saleHashControl.listIdUser(id).getHash();
		String hash2 = null;
		try {
			hash2 = saleHashControl.encrypt(user);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(hash.equals(hash2)) {
			return true;
		}else
		return false;
	}

	// ------------------------------------------------------
	private Sale verifyExist(int id) {
		Optional<Sale> retorno = saleRepository.findById(id);
		return retorno.orElseThrow(() -> new ResourceNotFoundException(MenssagemErro() + " not found to ID: " + id));

	}

	private String MenssagemErro() {
		String msg = "User";
		return msg;
	}

	private Sale transformEdit(SaleDTO userDTO) {
		return new Sale(userDTO.getId(), userDTO.getDescription(), userDTO.getValue());
	}

	private Sale transformSave(SaleDTO userDTO) {
		return new Sale(userDTO.getDescription(), userDTO.getValue());
	}

}
