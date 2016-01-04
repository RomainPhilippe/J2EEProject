package model;

import java.util.Date;

public class User {

	
	private Integer id_user;
	private String mail;
	private String password;
	private Date date;
	private String token;
	
	public Integer getId_user() {
		return id_user;
	}
	public void setId_user(Integer id_user) {
		this.id_user = id_user;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public User(Integer id_user, String mail, String password, Date date, String token) {
		this.id_user = id_user;
		this.mail = mail;
		this.password = password;
		this.date = date;
		this.token = token;
	}
	
	
	
}
