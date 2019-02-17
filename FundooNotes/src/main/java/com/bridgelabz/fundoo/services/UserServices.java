package com.bridgelabz.fundoo.services;


import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.LoginDTO;
import com.bridgelabz.fundoo.dto.UserDTO;
import com.bridgelabz.fundoo.model.User;

public interface UserServices {
	public void register(UserDTO userDTO) throws Exception;
	public boolean login(LoginDTO loginuser) throws Exception;
	public String validateEmailId(String token) throws Exception; 
	public void forgotPassword(String email) throws Exception;
	public void resetPassword(String token, String password) throws Exception;
}
