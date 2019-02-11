package com.bridgelabz.fundoo.services;


import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.LoginDTO;
import com.bridgelabz.fundoo.dto.UserDTO;
import com.bridgelabz.fundoo.model.User;

public interface UserServices {
	public User register(UserDTO userDTO) throws Exception;
	public boolean login(LoginDTO loginuser) throws Exception;
	public String validateEmailId(String token) throws Exception; 

}
