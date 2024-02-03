package com.raktKosh.model;

import java.util.Set;

import com.raktKosh.entities.Donor;
import com.raktKosh.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloodBankModel {

    
    private String name;
    private String address;
    private String phone;
    private String email;
	private String password;
	
}
