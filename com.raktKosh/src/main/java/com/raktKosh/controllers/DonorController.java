package com.raktKosh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raktKosh.entities.BloodBank;
import com.raktKosh.entities.Donor;
import com.raktKosh.services.BloodBankService;
import com.raktKosh.services.DonorService;

@RequestMapping("/donor")
@RestController
public class DonorController {

	@Autowired
	private DonorService donorService;
	
	@Autowired
	private BloodBankService bankService;
	
	@PutMapping("/{donorId}/bank/{bankId}")
	private Donor assignBloodBanktoDonor(@PathVariable Long donorId,@PathVariable Long bankId)
	{
		return donorService.assignBloodBanktoDonor(donorId,bankId);
	}
	
}
