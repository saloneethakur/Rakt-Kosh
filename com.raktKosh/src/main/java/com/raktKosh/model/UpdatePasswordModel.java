package com.raktKosh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordModel 
{
	private String OldPassword;
	private String NewPassword;
}
