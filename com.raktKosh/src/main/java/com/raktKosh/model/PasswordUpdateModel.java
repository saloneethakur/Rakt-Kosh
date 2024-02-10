package com.raktKosh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PasswordUpdateModel 
{
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
}
