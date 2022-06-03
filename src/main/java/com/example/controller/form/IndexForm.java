package com.example.controller.form;

import javax.validation.constraints.NotBlank;

public class IndexForm {
	
	@NotBlank
	private String loginId;
	@NotBlank
	private String pass;
	
	private String keyword;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}