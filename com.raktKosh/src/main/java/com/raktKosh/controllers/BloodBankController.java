package com.raktKosh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raktKosh.entities.Donor;
import com.raktKosh.services.DonorService;
import com.raktKosh.utils.ApiResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RequestMapping("/bloodbank")
@RestController
public class BloodBankController 
{
	@Autowired
	private DonorService donorService;
	/*
	@PostMapping("/add_donor")
	public ApiResponse saveDonor(@RequestBody Donor donor)
	{
		ApiResponse res = donorService.saveDonor(donor);
		return res;
	}*/
}
