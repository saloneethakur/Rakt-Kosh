package com.raktKosh.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raktKosh.entities.BloodBank;
import com.raktKosh.entities.Donor;
import com.raktKosh.entities.User;
import com.raktKosh.model.BloodBankUpdateModel;
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
	
	/*@PutMapping("/assign/{bankId}/donor/{donorId}")
	public BloodBank assignDonorToBloodBank(@PathVariable Long bankId,@PathVariable Long donorId)
	{
		return bloodbankService.assignDonorToBloodBank(bankId,donorId);
	}*/
	
	
	@PutMapping("/{donorId}/bank")
	public ApiResponse assignBloodBanktoDonor(@PathVariable (name="donorId") Long donorId)
	{
		Object priniciple = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final User USER = (User)priniciple;
		BloodBank bank =  bloodbankService.getBankById(USER);
	Long	bankId = bank.getBankId();
		ApiResponse obj = donorService.assignBloodBanktoDonor(donorId,bankId);
		 return new ApiResponse(true,"Blood Bank Details", obj);
	}
	
	@GetMapping("/list_donor")
	public ApiResponse getbankList() 
	{
		List<Donor> list = donorService.listAll();
		
		return new ApiResponse(true,"donor Records",list);
	}
	
	
	@PatchMapping("/updateBank")
	public ApiResponse update(@RequestBody BloodBankUpdateModel model)
	{
		Object priniciple = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final User USER = (User)priniciple;
		BloodBank bank =  bloodbankService.getBankById(USER);
		
		if(bank==null)
		{
			return new ApiResponse(false,"BloodBank not Found");
		}
		else
		{
			bank.update(model);
			bloodbankService.updateModel(bank);
			return new ApiResponse(true,"Blood Bank Updated");
		}
	}
}
