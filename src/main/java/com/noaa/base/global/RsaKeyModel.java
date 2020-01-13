package com.noaa.base.global;

import java.security.PrivateKey;
import java.security.PublicKey;

public class RsaKeyModel {
	public PrivateKey  privateKey;
	public PublicKey  publicKey;
	public String keyModule;
	public String keyExponent;
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	public PublicKey getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	public String getKeyModule() {
		return keyModule;
	}
	public void setKeyModule(String keyModule) {
		this.keyModule = keyModule;
	}
	public String getKeyExponent() {
		return keyExponent;
	}
	public void setKeyExponent(String keyExponent) {
		this.keyExponent = keyExponent;
	}
	
	
}
