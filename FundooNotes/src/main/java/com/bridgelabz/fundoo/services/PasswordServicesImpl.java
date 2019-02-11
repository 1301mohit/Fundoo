package com.bridgelabz.fundoo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.util.UserToken;
@Service
public class PasswordServicesImpl implements PasswordServices{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User forgotPassword(String email) throws Exception {
		Optional<User> userAvailable = userRepository.findByEmail(email);
		if(userAvailable.isPresent()) {
			return userAvailable.get();
		}
		else {
			throw new Exception("Email not found");
		}
	}

	@Override
	public User resetPassword(String token) throws Exception {
		Long id = UserToken.tokenVerify(token);
		return userRepository.findById(id).get();
	}

	
}
