package com.raktKosh.services;


import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.raktKosh.entities.BloodBank;
import com.raktKosh.entities.Donor;
import com.raktKosh.entities.User;
import com.raktKosh.model.BloodBankModel;
import com.raktKosh.repositories.BloodBankRepo;
import com.raktKosh.repositories.DonorRepo;
import com.raktKosh.utils.ApiResponse;

@Service
public class BloodBankService {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private  UserService userService;
	
	@Autowired
	private  MailService mailService;
	
	@Autowired
	private  BloodBankRepo bankrepo;
	
	
	
	@Autowired
	private  DonorRepo donorrepo;
	
	public  ApiResponse saveBank(BloodBankModel model) {
		ApiResponse response = null;
		try {
			
			User user = userService.saveUser( model.getEmail(),passwordEncoder.encode(model.getPassword()),"ROLE_BANK");
					
			BloodBank obj = new BloodBank(model.getName(),model.getAddress(),model.getPhone(),false,user);
			
			bankrepo.save(obj);
			
			mailService.verificationMail(model.getEmail(), model.getName());
			
			response = new ApiResponse(true, "bank Saved !");
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
			response = new ApiResponse(false, "bank Save Failed !", ex.getMessage());
		}		
		return response;
	}

	public BloodBank getBankById(User user) 
	{
		return bankrepo.findByUser(user).get();
	}
	
/*public BloodBank assignDonorToBloodBank(Long bankId, Long donorId) {
		
		Set<Donor> donorSet = null;
		BloodBank bank = bankrepo.findById(bankId).get();
		Donor donor = donorrepo.findById(donorId).get();
		donorSet = bank.getDonors();
		donorSet.add(donor);
		bank.setDonors(donorSet);
		
		 bankrepo.save(bank);
		 return bank;
		
		
	}*/

public List<BloodBank> listAll() {
	return bankrepo.findAll();
}

public void updateModel(BloodBank bank) 
{

	bankrepo.save(bank);
}


}
