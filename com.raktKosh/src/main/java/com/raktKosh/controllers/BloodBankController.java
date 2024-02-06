package com.raktKosh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raktKosh.entities.BloodBank;
import com.raktKosh.entities.Donor;
import com.raktKosh.entities.User;
import com.raktKosh.model.DonorModel;
import com.raktKosh.services.BloodBankService;
import com.raktKosh.services.DonorService;
import com.raktKosh.utils.ApiResponse;

import jakarta.validation.Valid;

@RequestMapping("/bloodbank")
@RestController
public class BloodBankController 
{
	@Autowired
	private DonorService donorService;
	
	@Autowired
	private BloodBankService bloodbankService;
	
	
	@PostMapping("/add_donor")
	public ApiResponse saveDonor(@Valid @RequestBody DonorModel donor)
	{
		ApiResponse res = donorService.saveDonor(donor);
		return res;
	}
	
	@GetMapping("/self_login")
	public ApiResponse selfLogin()
	{
		Object priniciple = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final User USER = (User)priniciple;
		BloodBank bank =  bloodbankService.getBankById(USER);
		
		return new ApiResponse(true,"Blood Bank Details", bank);
	}
}
