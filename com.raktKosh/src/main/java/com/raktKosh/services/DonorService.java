package com.raktKosh.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.raktKosh.entities.BloodBank;
import com.raktKosh.entities.Donor;
import com.raktKosh.entities.User;
import com.raktKosh.model.DonorModel;
import com.raktKosh.repositories.BloodBankRepo;
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
	
	@Autowired
	private  BloodBankRepo bankrepo;
	
	
	public ApiResponse saveDonor(DonorModel donor)
	{
		ApiResponse res = null; 
			
		try 
		{
			User user = userService.saveUser(donor.getEmail(), passencoder.encode(donor.getPassword()),"ROLE_DONOR");
			
			LocalDateTime today = LocalDateTime.now();
			Donor dono = new Donor(donor.getName(),donor.getPhone(),donor.getAddress(),donor.getGender(),donor.getDob(),donor.getBloodGroup(),donor.getAadharCard(),donor.getLastDonateDate(), "BloodBank", user,false,today);
			
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
	
	public ApiResponse assignBloodBanktoDonor(Long donorId, Long bankId) {
		ApiResponse res = null; 
		try
		{
			Set<BloodBank> bankSet = null;
			BloodBank bank = bankrepo.findById(bankId).get();
			Donor donor = donorRepo.findById(donorId).get();
			bankSet = donor.getBanks();
			bankSet.add(bank);
			donor.setBanks(bankSet);
			 donorRepo.save(donor);
			 res= new ApiResponse(true,"Donor Saved",donor);
		}
		
		catch(Exception e)
		{
			res= new ApiResponse(false,e.getMessage());
		}
		 return res;
	}

	public List<Donor> listAll() {
		return donorRepo.findAll();
	}

	public Donor getDonorById(User USER) {
		return donorRepo.findByUser(USER).get();
	}

	public void updateModel(Donor donor) 
	{
		donorRepo.save(donor);
	}
}
