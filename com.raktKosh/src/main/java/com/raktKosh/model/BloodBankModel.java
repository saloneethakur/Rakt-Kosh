package com.raktKosh.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloodBankModel {

	@NotEmpty
    private String name;
	
	@NotEmpty
    private String address;
    
	@NotEmpty
    private String phone;
    
	@NotEmpty
    @Email
    private String email;
    
	@NotEmpty
	private String password;
	
}
