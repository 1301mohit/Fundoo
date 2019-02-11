package com.bridgelabz.fundoo.services;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.LoginDTO;
import com.bridgelabz.fundoo.dto.UserDTO;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.util.EmailUtil;
import com.bridgelabz.fundoo.util.UserToken;

@Service
public class UserServicesImpl implements UserServices {
	
	
	

	@Autowired
	private UserRepository userRepository;
	
	
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	 
	 
	
	@Autowired
	private ModelMapper modelMapper;
	/**
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public User register(UserDTO userDTO) throws Exception {
		
		Optional<User> useravailable = userRepository.findByEmail(userDTO.getEmail());
		if(useravailable.isPresent())
		{
			throw new Exception("Dublicate user found");
			//throw new UserException(100,"Duplicate user found");
		}
		User user=modelMapper.map(userDTO, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	    user = userRepository.save(user);
	    EmailUtil.send(user.getEmail(), "mail for Registration", getUrl(user.getId()));
		return user;
	}
	
	@Override
	public boolean login(LoginDTO loginuser) throws Exception {
		Optional<User> useravailable = userRepository.findByEmail(loginuser.getEmail());
		System.out.println("database password"+useravailable.get().getPassword());
		System.out.println("login user password"+passwordEncoder.encode(loginuser.getPassword()));
		if(useravailable.isPresent() && passwordEncoder.matches(loginuser.getPassword(),useravailable.get().getPassword()) && useravailable.get().isIsverification()) 
		{ 
			return true; 
		} 
		else 
		{ 
			throw new Exception("Email and Password is not found"); 
		}
	}

	@Override
	public String validateEmailId(String token) throws Exception {
		Long id = UserToken.tokenVerify(token);
		User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found")); 
		user.setIsverification(true);
		userRepository.save(user);
		return "Successfully verified";
	}
	
	
	public String getUrl(Long id) throws Exception{
		return "192.168.0.84:8080/user/"+UserToken.generateToken(id);
	}
	
	
//	@Override
//	public User adduser(UserDTO userDto) {
//		User user = modelMapper.map(userDto, User.class);
//		userRepository.save(user);
//		return null;
//	}
}
