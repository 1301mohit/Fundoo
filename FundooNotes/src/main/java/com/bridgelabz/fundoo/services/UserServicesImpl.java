package com.bridgelabz.fundoo.services;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.bridgelabz.fundoo.dto.LoginDTO;
import com.bridgelabz.fundoo.dto.UserDTO;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.response.Response;
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
	
	@Autowired
	private Response response;
	
	/**
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public Response register(UserDTO userDTO) throws Exception 
	{	
		Optional<User> useravailable = userRepository.findByEmail(userDTO.getEmail());
		if(useravailable.isPresent())
		{
			throw new Exception("Dublicate user found");
		}
		User user=modelMapper.map(userDTO, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	    user = userRepository.save(user);
	    EmailUtil.send(user.getEmail(), "mail for Registration", getUrl(user.getId()));
	    response.setStatusCode(100);
	    response.setStatusMessage(environment.getProperty("1"));
	    return response;
	}
	
	@Override
	public Response login(LoginDTO loginuser) throws Exception 
	{
		Optional<User> userAvailable = userRepository.findByEmail(loginuser.getEmail());
		System.out.println("database password"+userAvailable.get().getPassword());
		System.out.println("login user password"+passwordEncoder.encode(loginuser.getPassword()));
		if(userAvailable.isPresent() && passwordEncoder.matches(loginuser.getPassword(),userAvailable.get().getPassword()) && userAvailable.get().isIsverification()) 
		{ 
			String generateToken = UserToken.generateToken(userAvailable.get().getId());
			response.setStatusCode(200);
			response.setStatusMessage(environment.getProperty("2"));
			response.setToken(generateToken);
			return response; 
		} 
		else 
		{ 
			throw new Exception("Email and Password is not found"); 
		}
	}

	@Override
	public String validateEmailId(String token) throws Exception 
	{
		Long id = UserToken.tokenVerify(token);
		User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found")); 
		user.setIsverification(true);
		userRepository.save(user);
		return "Successfully verified";
	}
	
	
	public String getUrl(Long id) throws Exception
	{
		return "192.168.0.84:8080/user/"+UserToken.generateToken(id);
	}
	
	@Override
	public Response forgotPassword(String email) throws Exception 
	{
		Optional<User> userAvailable = userRepository.findByEmail(email);
		if(userAvailable.isPresent()) 
		{
			User user = userAvailable.get();
			EmailUtil.send(email, "Password Reset", Utility.getBody(user, "user"));
			response.setStatusCode(300);
			response.setStatusMessage(environment.getProperty("3"));
			return response;
		}
		else 
		{
			throw new Exception("Email not found");
		}
	}
	
	@Override
	public Response resetPassword(String token, String password) throws Exception
	{
			long userId = UserToken.tokenVerify(token);
			System.out.println(userId);
			User user = userRepository.findById(userId).get();
			System.out.println(user.getPassword());
			user.setPassword(passwordEncoder.encode(password));
			System.out.println(user.getPassword());
			userRepository.save(user);
			response.setStatusCode(400);
			response.setStatusMessage(environment.getProperty("4"));
			return response;
	}
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

