package com.bridgelabz.fundoo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

//import org.hibernate.validator.constraints.UniqueElements;

//import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name="User_Details")
public class User {
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)//
//	private long id;
//	private String name;
//	private String email;
//	private String password;
//	
//	public long getId() {
//		return id;
//	}
//	public void setId(long id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message="please fill the name")
	private String userName;
	
	//@UniqueElements
	@Column(unique = true)
	@NotEmpty(message="please fill the email")
	private String email;
	
	@Pattern(regexp = "[0-9]{10}", message = "Number Should Only Be Digit And 10 digit only")
	@NotEmpty(message="please fill the mobilenumber")
	private String mobileNumber;
	
	@NotEmpty(message="please fill the password")
	private String password;
	
	private boolean isVerification;
	
	public User() 
	{
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String name) {
		this.userName = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean isIsverification() {
		return isVerification;
	}

	public void setIsverification(boolean isverification) {
		this.isVerification = isverification;
	}

	/*public static long getSerialversionuid() {
		return serialVersionUID;
	}*/

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + userName + ", email=" + email + ", mobileNumber=" + mobileNumber
				+ ", password=" + password + ", isverification=" + isVerification + "]";
	}
}
