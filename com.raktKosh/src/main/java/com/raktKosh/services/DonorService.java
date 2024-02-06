package com.raktKosh.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.raktKosh.entities.Donor;
import com.raktKosh.entities.User;
import com.raktKosh.model.DonorModel;
import com.raktKosh.repositories.DonorRepo;
import com.raktKosh.utils.ApiResponse;



@Service
public class DonorService 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	PasswordEncoder passencoder;
	
	@Autowired
	DonorRepo donorRepo;
	
	@Autowired
	MailService mailService;
	
	public ApiResponse saveDonor(DonorModel donor)
	{
		ApiResponse res = null; 
			
		try 
		{
			User user = userService.saveUser(donor.getEmail(), passencoder.encode(donor.getPassword()),"ROLE_DONOR");
			
			
			Donor dono = new Donor(donor.getName(),donor.getPhone(),donor.getAddress(),donor.getGender(),donor.getDob(),donor.getBloodGroup(),donor.getAadharCard(),donor.getLastDonateDate(), "BloodBank", user,false);
			
			donorRepo.save(dono);
			
			mailService.verificationMail(donor.getEmail(),donor.getName());
			
			res= new ApiResponse(true,"Donor Saved");
		}
		catch(Exception e)
		{
			res= new ApiResponse(false,e.getMessage());
		}
		return res;
	}
}
