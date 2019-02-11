package com.bridgelabz.fundoo.services;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.model.User;

public interface PasswordServices {

	User forgotPassword(String email) throws Exception;
	
	User resetPassword(String token) throws Exception;
	
}
