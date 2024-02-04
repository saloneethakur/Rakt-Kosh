package com.raktKosh.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginModel 
{
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String password;
}
