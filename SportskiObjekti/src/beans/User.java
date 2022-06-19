package beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.Gender;

public class User {
	
	public User(String username, String password, String name, String last_name, Gender gender, Date birthDate) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.last_name = last_name;
		this.gender = gender;
		this.birthDate = birthDate;
	}
	private String username;
	private String password;
	private String name;
	private String last_name;
	private utils.Gender gender;
	private Date birthDate;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public utils.Gender getGender() {
		return gender;
	}
	public void setGender(utils.Gender gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getUserString() {
		char genderChar;
		if(this.gender==Gender.MALE) genderChar='M'; else genderChar='F';
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy.");  
		String strDate = dateFormat.format(this.birthDate); 
		String s=this.username+";"+this.password+";"+this.name+";"+this.last_name+";"+genderChar+";"+strDate;
		return s;
	}
	
}