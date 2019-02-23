package com.bridgelabz.fundoo.services;


import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.LoginDTO;
import com.bridgelabz.fundoo.dto.UserDTO;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.response.Response;

public interface UserServices {
	
	public Response register(UserDTO userDTO) throws Exception;
	public Response login(LoginDTO loginuser) throws Exception;
	public String validateEmailId(String token) throws Exception; 
	public Response forgotPassword(String email) throws Exception;
	public Response resetPassword(String token, String password) throws Exception;
	
}
