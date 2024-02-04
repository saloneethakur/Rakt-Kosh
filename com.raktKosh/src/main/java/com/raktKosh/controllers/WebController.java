package com.raktKosh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raktKosh.config.JwtTokenUtil;
import com.raktKosh.entities.User;
import com.raktKosh.model.LoginModel;
import com.raktKosh.model.LoginResponseModel;
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
	private JwtTokenUtil jwtToken;
	
	@PostMapping("/login")
	public ApiResponse login_user(@Valid @RequestBody LoginModel loginmodel)
	{
		try {
		authmanager.authenticate(new UsernamePasswordAuthenticationToken(loginmodel.getEmail(), loginmodel.getPassword()));
		User user = (User)userService.loadUserByUsername(loginmodel.getEmail());
		String token = jwtToken.generateToken(user);
		
		LoginResponseModel lres = new LoginResponseModel(user.getUsername(),token,user.getRole());
		
		return new ApiResponse(true, "Login Success!!", lres);
		}
		catch (Exception e) 
		{
			return new ApiResponse(false,":Login Failed");
		}
	}
}
