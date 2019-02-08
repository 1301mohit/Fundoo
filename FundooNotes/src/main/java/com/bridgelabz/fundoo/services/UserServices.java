package com.bridgelabz.fundoo.services;


import com.bridgelabz.fundoo.dto.LoginDTO;
import com.bridgelabz.fundoo.dto.UserDTO;
import com.bridgelabz.fundoo.model.User;

public interface UserServices {
	public User register(UserDTO userDTO) throws Exception;
	//public User adduser(UserDTO userDto);
	public boolean login(LoginDTO loginuser) throws Exception;

}
