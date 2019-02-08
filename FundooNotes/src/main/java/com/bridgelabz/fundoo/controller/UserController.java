package com.bridgelabz.fundoo.controller;

//import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.LoginDTO;
import com.bridgelabz.fundoo.dto.UserDTO;
//import com.bridgelabz.fundoo.exception.UserException;
//import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.model.User;
//import com.bridgelabz.fundoo.response.Response;
//import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.services.UserServices;
//import com.bridgelabz.fundoo.util.EmailUtil;

@RestController
//@RequestMapping("/bridgelabz/fundoo")
public class UserController {
	static Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserServices userServices;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) throws Exception//UserException
	{
		User user = null;
        logger.trace("User Registration");
		if(bindingResult.hasErrors()) {
			logger.error("Error in Binding The User Details");
			throw new Exception("Data Not Matching");
		}
		try {
			user = userServices.register(userDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Registration Successfull", HttpStatus.OK);
		//	System.out.println("Registered");
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception
	{
		logger.trace("Login");
		boolean flag = false;
		try {
			flag = userServices.login(loginDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag)
			return new ResponseEntity<String>("Login Successfull", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Login Unsuccessfull", HttpStatus.NOT_FOUND);
		
	}
	
	/*@RequestMapping(value = "/sendemail")
	public String sendEmail() {
	    return "Email sent successfully";
	} */  
	
//	@PostMapping("/add")
//	public void add(@RequestBody UserDTO userDTO)
//	{
//		userServices.adduser(userDTO);
//		System.out.println("Added");
//	}
	
//	//@RequestMapping("/test")
//	@PostMapping("/test")
//	public String test()
//	{
//		//return new ResponseEntity(HttpStatus.OK);
//
//		return "Test";
//	}
}
