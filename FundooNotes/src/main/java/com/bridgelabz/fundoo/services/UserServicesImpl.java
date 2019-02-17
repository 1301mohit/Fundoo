package com.bridgelabz.fundoo.services;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.LoginDTO;
import com.bridgelabz.fundoo.dto.UserDTO;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.util.EmailUtil;
import com.bridgelabz.fundoo.util.UserToken;
import com.bridgelabz.fundoo.util.Utility;

@Service
@PropertySource("classpath:message.properties")
public class UserServicesImpl implements UserServices {
	
	@Autowired
	public Environment environment;

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
	public void register(UserDTO userDTO) throws Exception {
		
		Optional<User> useravailable = userRepository.findByEmail(userDTO.getEmail());
		if(useravailable.isPresent())
		{
			throw new Exception("Dublicate user found");
			//throw new UserException(100,"Duplicate user found");
		}
		//System.out.println(environment.getProperty("a"));
		User user=modelMapper.map(userDTO, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	    user = userRepository.save(user);
	    //System.out.println(user.getUserName());
	    EmailUtil.send(user.getEmail(), "mail for Registration", getUrl(user.getId()));
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
	
	@Override
	public void forgotPassword(String email) throws Exception {
		Optional<User> userAvailable = userRepository.findByEmail(email);
		if(userAvailable.isPresent()) {
			User user = userAvailable.get();
			EmailUtil.send(email, "Password Reset", Utility.getBody(user, "Reset Password"));
		}
		else {
			throw new Exception("Email not found");
		}
	}
	
	@Override
	public void resetPassword(String token, String password) throws Exception
	{
			long userId = UserToken.tokenVerify(token);
			User user = userRepository.findById(userId).get();
			user.setPassword(passwordEncoder.encode(password));
			userRepository.save(user);
	}

//	@Override
//	public User resetPassword(String token) throws Exception {
//		Long id = UserToken.tokenVerify(token);
//		return userRepository.findById(id).get();
//	}
//	
//	public String getBody(User user, String link) throws Exception {
//		return "192.168.0.84:8080/"+link+UserToken.generateToken(user.getId());
//	}
}
