package com.raktKosh.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raktKosh.entities.BloodBank;
import com.raktKosh.entities.Donor;
import com.raktKosh.entities.User;
import com.raktKosh.services.BloodBankService;
import com.raktKosh.services.DonorService;
import com.raktKosh.utils.ApiResponse;

@RequestMapping("/donor")
@RestController
public class DonorController {

	@Autowired
	private DonorService donorService;
	
	@Autowired
	private BloodBankService bankService;
	
	@GetMapping("/self_login")
	public ApiResponse selfLogin()
	{
		Object priniciple = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final User USER = (User)priniciple;
		
		Donor donor = donorService.getDonorById(USER);
		
		return new ApiResponse(true,"Blood Bank Details", donor);
	}
	
	
}
