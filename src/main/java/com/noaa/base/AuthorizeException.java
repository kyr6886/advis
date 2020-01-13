package com.noaa.base;

public class AuthorizeException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private boolean isDeny;
	private boolean isLogin;
	private String view;
	private int errCode;
	
	public AuthorizeException(int errCode,boolean isDeny,boolean isLogin,String view){
		super(MessageStatus.valueOf(errCode).getReasonPhrase());
		this.errCode=errCode;
		this.isDeny=isDeny;
		this.isLogin=isLogin;
		this.view=view;
	}
	public int getErrorCode(){
		return errCode;
	}
	public boolean getIsDeny(){
		return isDeny;
	}
	public boolean getIsLogin(){
		return isLogin;
	}
	public String getView(){
		return view;
	}
}
