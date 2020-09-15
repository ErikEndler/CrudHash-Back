package com.CrudHashBack.Control;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CrudHashBack.Models.Sale;
import com.CrudHashBack.Models.SaleHash;
import com.CrudHashBack.Repository.SaleHashRepository;
import com.CrudHashBack.Util.Encryption;

@Service
public class SaleHashControl {

	Encryption encryption = new Encryption();
	@Autowired
	SaleHashRepository saleHashRepository;

	public String encrypt(Sale sale) throws NoSuchAlgorithmException {
		String string = sale.getDescription() + sale.getValue();
		System.out.println("STRING :" + string);
		String result = encryption.encrypter(string);
		return result;
	}

	public Sale save(Sale sale) throws NoSuchAlgorithmException {
		String hash = encrypt(sale);
		SaleHash userHash = saleHashRepository.save(new SaleHash(hash, sale));
		sale.setHash(userHash);
		return sale;
	}

	public Sale edit(Sale sale) {
		try {
			String hash = encrypt(sale);
			SaleHash saleHash= listIdUser(sale.getId());
			saleHash.setHash(hash);
			saleHash = saleHashRepository.save(saleHash);
			sale.setHash(saleHash);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sale;
	}

	public SaleHash listIdUser(int id) {
		return saleHashRepository.findBySaleId(id);
	}

	public void list() {

	}

	public void delete(int id) {
		SaleHash userhash = listIdUser(id);
		saleHashRepository.deleteById(userhash.getId());

	}

}
