package com.bridgelabz.fundoo.controller;

import javax.validation.Valid;

//import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoo.dto.LoginDTO;
import com.bridgelabz.fundoo.dto.UserDTO;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserRepository;

//import com.bridgelabz.fundoo.exception.UserException;
//import com.bridgelabz.fundoo.exception.UserException;
//import com.bridgelabz.fundoo.response.Response;
//import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.services.UserServices;
//import com.bridgelabz.fundoo.util.EmailUtil;
import com.bridgelabz.fundoo.util.EmailUtil;
import com.bridgelabz.fundoo.util.UserToken;

@RestController
//@RequestMapping("/bridgelabz/fundoo")
@PropertySource("classpath:message.properties")
public class UserController {
	
//	@Autowired
//	private UserRepository userRepository;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	static Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public Environment environment;
	
	@Autowired
	private UserServices userServices;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody UserDTO userDTO /*BindingResult bindingResult*/) throws Exception//UserException
	{
		System.out.println("helo");
		  boolean flag = false;
		  logger.trace("User Registration");
//		  if(bindingResult.hasErrors()) 
//		  {
//			  logger.error("Error in Binding The User Details"); 
//			  throw new Exception("Data Not Matching"); 
//		  }
		  
		userServices.register(userDTO);
		return new ResponseEntity<String>(environment.getProperty("register"), HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception
	{
		logger.trace("Login");
		boolean flag = false;
		
		flag = userServices.login(loginDTO);
			
		if(flag)
			return new ResponseEntity<String>("Login Successfull", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Login Unsuccessfull", HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/user/{token}")
	public String emailValidation(@PathVariable String token) throws Exception
	{
		String result = userServices.validateEmailId(token);
		return result;
	}

	@PostMapping("/forgotpassword")
	public ResponseEntity<?> forgetPassword(@RequestParam String email) throws Exception
	{
		System.out.println("forgetPassword");
		logger.info("Password Recovery");
		userServices.forgotPassword(email);
		return new ResponseEntity<String>("Reset-Mail Send To Your Eamil Address", HttpStatus.OK);
	}
	

	
	@PutMapping("{token}/resetPassword")
	public ResponseEntity<?> resetPassword(@PathVariable String token, @RequestParam String password) throws Exception
	{
		userServices.resetPassword(token, password);
		return new ResponseEntity<String>("Password reset",HttpStatus.OK);
	}
	
//	@GetMapping("/resetPassword/{token}")
//	public ResponseEntity<?> resetPassword(@PathVariable("token") String token,) throws Exception{
//		logger.info("password reset");
//		User user = userServices.resetPassword(token);
//		EmailUtil.send(user.getEmail(), "Change Password", .getBody(user, "reset page"));
//		return new ResponseEntity<String>("Redirect to new password set pqage",HttpStatus.OK);
//	}
	
//	@PostMapping("/resetPage/{token}")
//	public ResponseEntity<?> resetPage(@PathVariable("token") String token, @RequestBody LoginDTO loginUser) throws Exception{
//		logger.info("Password reset page");
//		long userId = UserToken.tokenVerify(token);
//		User user = userRepository.findById(userId).get();
//		user.setPassword(passwordEncoder.encode(loginUser.getPassword()));
//		userRepository.save(user);
//		return new ResponseEntity<String>("Password Set Successfully",HttpStatus.OK);
//	}
	
	/*private String getBody(User user, String link) {
		return "192.168.0.84:8080/"+link+UserToken.generateToken(user.getId());
	}*/
	
}
