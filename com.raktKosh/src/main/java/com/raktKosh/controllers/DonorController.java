package com.raktKosh.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raktKosh.entities.BloodBank;
import com.raktKosh.entities.Donor;
import com.raktKosh.entities.User;
import com.raktKosh.model.BloodBankUpdateModel;
import com.raktKosh.model.DonorUpdateModel;
import com.raktKosh.services.DonorService;
import com.raktKosh.utils.ApiResponse;

@RequestMapping("/donor")
@RestController
public class DonorController {

	@Autowired
	private DonorService donorService;
	
	
	@GetMapping("/self_login")
	public ApiResponse selfLogin()
	{
		Object priniciple = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final User USER = (User)priniciple;
		
		Donor donor = donorService.getDonorById(USER);
		
		return new ApiResponse(true,"Donor Details", donor);
	}
	
	

	
	@PatchMapping("/updateDonor")
	public ApiResponse update(@RequestBody DonorUpdateModel model)
	{
		Object priniciple = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final User USER = (User)priniciple;
		Donor donor =  donorService.getDonorById(USER);
		
		if(donor==null)
		{
			return new ApiResponse(false,"Donor not Found");
		}
		else
		{
			donor.update(model);
			donorService.updateModel(donor);
			return new ApiResponse(true,"Donor Updated");
		}
	}

}
