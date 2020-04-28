package com.mautentication.autentication.dto;

public class User {
	public User(String mail, String mailAlt, String name, String photo, String telephone, String uId) {
		this.mail = mail;
		this.mailAlt = mailAlt;
		this.name = name;
		this.photo = photo;
		this.telephone = telephone;
		this.uId = uId;
	}

	private String mail;
	private String mailAlt;
	private String name;
	private String photo;
	private String telephone;
	private String uId;


	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMailAlt() {
		return mailAlt;
	}

	public void setMailAlt(String mailAlt) {
		this.mailAlt = mailAlt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}



}
