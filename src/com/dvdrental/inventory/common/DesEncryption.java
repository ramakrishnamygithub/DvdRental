package com.dvdrental.inventory.common;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DesEncryption {
	public static final String DESEDE_ENCRYPTION_SCHEME="DEsede";
	public static final String DES_ENCRYPTION_SCHEME="DES";

	public static final String DEFAULT_ENCRYPTION_KEY="This is a fairly long phrase user to encrypt ";

	public static final String UNICODE_FORMAT="UTF8";

	private KeySpec keySpec;
	private SecretKeyFactory keyFactory;
	private Cipher cipher;

   public DesEncryption(String encryptionScheme)throws EncryptionException{
	   this(encryptionScheme,DEFAULT_ENCRYPTION_KEY);
	   
   }
   public DesEncryption(String encryptionScheme,String encryptoinKey)throws EncryptionException{
	  if(encryptoinKey==null) {
		  throw new IllegalArgumentException("encryption key was null");
	  }
	  if(encryptoinKey.trim().length()<24) {
		  throw new  IllegalArgumentException("encryption key was less than 24 characters");
	  }
	   try {
		   byte[] keyAsBytes=encryptoinKey.getBytes(UNICODE_FORMAT);
		   if(encryptionScheme.equals(DESEDE_ENCRYPTION_SCHEME)) {
			   keySpec=new DESedeKeySpec(keyAsBytes);
		   }else if(encryptionScheme.equals(DES_ENCRYPTION_SCHEME)) {
			   keySpec=new DESKeySpec(keyAsBytes);
			   
		   }else {
			   throw new IllegalArgumentException("Encryption sheme not supported : "+encryptionScheme);
		   }
		   keyFactory=SecretKeyFactory.getInstance(encryptionScheme);
		   cipher=Cipher.getInstance(encryptionScheme);
	   }
	   catch(InvalidKeyException e) {
		   throw new EncryptionException(e);
	   }
	   catch(UnsupportedEncodingException e) {
		   throw new EncryptionException(e);
		   
	   }
	   catch(NoSuchAlgorithmException e) {
		   throw new EncryptionException(e);
		   
	   }
	   catch( NoSuchPaddingException e) { 
		   throw new EncryptionException(e);
		   
	   }
	   
   }
   
   public String encrypt(String unencryptedString) throws EncryptionException{
	   if((unencryptedString==null) || (unencryptedString.trim().length()==0)){
		   throw new IllegalArgumentException("unencrpted string was null or empty");
	   
	   }
	   try {
		   SecretKey key=keyFactory.generateSecret(keySpec);
		   cipher.init(Cipher.ENCRYPT_MODE, key);
		   byte[] cleartext=unencryptedString.getBytes(UNICODE_FORMAT);
		   byte[] ciphertext=cipher.doFinal(cleartext);
		   BASE64Encoder base64encoder=new BASE64Encoder();
		   return base64encoder.encode(ciphertext);
	   }catch(Exception e) {
		   throw new EncryptionException(e);
	   }
	   
   }
   public String decrypt(String encryptedString) throws EncryptionException{
	   if((encryptedString==null) || (encryptedString.trim().length()==0)){
		   throw new IllegalArgumentException("encryptedString string was null or empty");
	   
	   }
	   try {
		   SecretKey key=keyFactory.generateSecret(keySpec);
		   cipher.init(Cipher.DECRYPT_MODE, key);
		   BASE64Decoder base64decoder=new BASE64Decoder();
		   byte[] cleartext=base64decoder.decodeBuffer(encryptedString);
		   byte[] ciphertext=cipher.doFinal(cleartext);
		  
		   return bytes2String(ciphertext);
	   }catch(Exception e) {
		   throw new EncryptionException(e);
	   }
	   
   }
   private static String bytes2String(byte[] bytes) {
	   StringBuffer stringBuffer=new StringBuffer();
	   for(int i=0;i<bytes.length;i++) {
		   stringBuffer.append((char)bytes[i]);
		   
	   }
	   return stringBuffer.toString();
   }
   public static class EncryptionException extends Exception{
	   public EncryptionException(Throwable t) {
		   super(t);
	   }
   }

}
