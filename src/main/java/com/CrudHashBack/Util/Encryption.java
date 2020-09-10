package com.CrudHashBack.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {

	public String encrypter(String texto) throws NoSuchAlgorithmException {
		// String s = "senha aqui";
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		System.out.println("texto a ser criptografado : " + texto);
		md.update(texto.getBytes());
		byte[] hash = md.digest();
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			if ((0xff & hash[i]) < 0x10)
				hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
			else
				hexString.append(Integer.toHexString(0xFF & hash[i]));
		}
		String criptografado = hexString.toString();
		System.out.println("String criptografada 'HASH' : " + criptografado);
		return criptografado;
	}

	private static SecretKey key;
	

	public String metodo2(String mensagem) {
		try {
			
			String chaveSimetrica = "chavesimetricagrande";
			key = new SecretKeySpec(chaveSimetrica.getBytes(), "AES");
			byte[] mensagemEncriptada;

			Cipher cipher = Cipher.getInstance("AES");

			cipher.init(Cipher.ENCRYPT_MODE, key);
			/* Solicita ao usuŕio que informe sua mensagem a ser encriptada */
			System.out.println("Informe sua mensagem a ser encriptada: ");
			// mensagem = sc.nextLine();
			/* Encripta a Mensagem */
			mensagemEncriptada = cipher.doFinal(mensagem.getBytes());
			/* Exibe Mensagem Encriptada */
			System.out.println(new String("Mensagem Encriptada: " + mensagemEncriptada));
			/* Informa ao objeto a ação de desencriptar */
			cipher.init(Cipher.DECRYPT_MODE, key);
			/* Recebe a mensagem encriptada e descripta */
			byte[] mensagemDescriptada = cipher.doFinal(mensagemEncriptada);
			/**
			 * Converte para a base 64 e amazena a mensagem em uma variavel auxiliar
			 */
			String mensagemOriginal = new String(mensagemDescriptada);

			/* Exibe Mensagem Descriptada */
			System.out.println("Mensagem Descriptada: " + mensagemOriginal);
			String str = new String(mensagemEncriptada);
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
