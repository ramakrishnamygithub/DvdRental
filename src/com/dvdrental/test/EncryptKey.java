package com.dvdrental.test;

import com.dvdrental.inventory.common.DesEncryption;
import com.dvdrental.inventory.common.DesEncryption.EncryptionException;
import com.dvdrental.inventory.common.IConstants;

public class EncryptKey {

	public static void main(String[] args) throws EncryptionException {

		DesEncryption encryptor=new DesEncryption(DesEncryption.DESEDE_ENCRYPTION_SCHEME,IConstants.ENCRYPT_KEY);
		String ecryptedPassword="6b3nvTQOgCF7XLrq12ev1g==";
		String decryptedPassword="";
		
		
		/*
		 * decryptedPassword=encryptor.decrypt(ecryptedPassword);
		 * System.out.println("decryptedPassword=="+decryptedPassword);
		 */
		 
		
		
		  decryptedPassword="rk@getset";
		  ecryptedPassword=encryptor.encrypt(decryptedPassword);
		  System.out.println("decryptedPassword==="+ecryptedPassword);
		 
		
		
		
	}

}
