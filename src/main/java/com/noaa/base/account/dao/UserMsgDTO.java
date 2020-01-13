package com.noaa.base.account.dao;

import com.noaa.base.BaseDTO;

public class UserMsgDTO extends BaseDTO {
	
	private String user_id;
	private String msg_content;
	private String msg_type_code; 
    private int msg_seq;
	
    public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public String getMsg_type_code() {
		return msg_type_code;
	}
	public void setMsg_type_code(String msg_type_code) {
		this.msg_type_code = msg_type_code;
	}
	public int getMsg_seq() {
		return msg_seq;
	}
	public void setMsg_seq(int msg_seq) {
		this.msg_seq = msg_seq;
	}
    
    

}
