package com.raktKosh.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.raktKosh.config.JwtTokenUtil;
import com.raktKosh.entities.User;
import com.raktKosh.model.LoginModel;
import com.raktKosh.model.LoginResponseModel;
import com.raktKosh.services.MailService;
import com.raktKosh.services.UserService;
import com.raktKosh.utils.ApiResponse;

import jakarta.validation.Valid;

@RequestMapping("/web")
@RestController
public class WebController 
{
	@Autowired
	private AuthenticationManager authmanager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	
	
	
	@Autowired
	private JwtTokenUtil jwtToken;
	
	
	@PostMapping("/login")
	public ApiResponse loginUser(@RequestBody LoginModel loginModel) 
	{
		try {
			authmanager.authenticate(new UsernamePasswordAuthenticationToken(loginModel.getEmail(),loginModel.getPassword()));

		User	user =  (User) userService.loadUserByUsername(loginModel.getEmail());
			
			if(user.isActiveStatus())
			{
				final String token = jwtToken.generateToken(user);
				
				LoginResponseModel lres = new LoginResponseModel(user.getEmail(), token, user.getRole());
				
				return new ApiResponse(true, "Login Success !", lres);
			}else 
			{
				return new ApiResponse(false, "Inactive Account , Please Activate it through Mail !");
			}
		}catch(Exception ex) {
			return new ApiResponse(false, "Login Failed !", null,ex.getMessage());
		}
	}
	
	@GetMapping("/verify/{mail}")
	public ApiResponse verifyMail(@PathVariable(name = "mail") String email) 
	{
		Optional<User> op = userService.findByEmail(email);
		if(op.isPresent()) 
		{
			User user = op.get();
			user.setActiveStatus(true);
			userService.update(user);
			
			return new ApiResponse(true, "Account Activated !");
		}else {
			return new ApiResponse(false, "Wrong Email !");
		}
	}
	
	@GetMapping("/resendlink/{mail}")
	public ApiResponse resendMail(@PathVariable(name = "mail") String email) 
	{
		Optional<User> op = userService.findByEmail(email);
		if(op.isPresent()) 
		{
			try {
				mailService.verificationMail(email, "User");
				return new ApiResponse(true, "Mail Send Successfully !");
			} catch (Exception e) {
				return new ApiResponse(false, "Mail Send Failed !");
			}
			
		}else {
			return new ApiResponse(false, "Wrong Email !");
		}
	}
	
}
