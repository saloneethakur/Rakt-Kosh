package com.raktKosh.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raktKosh.model.PasswordUpdateModel;
import com.raktKosh.services.UserService;
import com.raktKosh.utils.ApiResponse;

@RequestMapping("/user")
@RestController
public class UserController 
{
	@Autowired
	private UserService userService;

	@PatchMapping("/passwordUpdate")
	public ApiResponse changePass(@RequestBody PasswordUpdateModel model, Principal  connectedUser)
	{
		Boolean b = userService.changePassword(model,connectedUser);
		
		if(b)
		{
			return new ApiResponse(true,"Password Updated");
		}
		else
		{
			return new ApiResponse(false,"Password not updated");
		}
	}	
}
