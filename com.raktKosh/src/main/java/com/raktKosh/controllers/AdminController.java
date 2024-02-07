package com.raktKosh.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raktKosh.entities.BloodBank;
import com.raktKosh.model.BloodBankModel;
import com.raktKosh.services.BloodBankService;
import com.raktKosh.utils.ApiResponse;



@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private BloodBankService bankservice;
	
	@PostMapping("/save_BloodBank")
	public ApiResponse savebank(@RequestBody BloodBankModel model) 
	{
		ApiResponse response =  bankservice.saveBank(model);
		return response;
	}
	
	@GetMapping("/list_banks")
	public ApiResponse getbankList() 
	{
		List<BloodBank> list = bankservice.listAll();
		
		return new ApiResponse(true,"Bank Records",list);
	}
}
