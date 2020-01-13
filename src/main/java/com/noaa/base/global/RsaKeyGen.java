package com.noaa.base.global;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

public class RsaKeyGen {
	private static RsaKeyGen _instance;
	public static RsaKeyGen getInstance(){
		if(_instance==null){
			_instance=new RsaKeyGen();
		}
		return _instance;
	}
	
	public RsaKeyModel createRsaKeys(){
		RsaKeyModel rs=null;
		try{
			KeyPairGenerator gen=KeyPairGenerator.getInstance("RSA");
			gen.initialize(512);
			KeyPair keyPair = gen.genKeyPair();
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();
	
			
			// 공개키를 문자열로 변환하여 JavaScript RSA 라이브러리로 넘겨준다.
			RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
	
			String publicKeyModulus = publicSpec.getModulus().toString(16);
			String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
			rs=new RsaKeyModel();
			rs.setKeyExponent(publicKeyExponent);
			rs.setKeyModule(publicKeyModulus);
			rs.setPrivateKey(privateKey);
			rs.setPublicKey(publicKey);
		}catch(Exception ex){
		  System.out.println(ex.getMessage());
		}
		return rs;
	}
	
	
	
	
}
