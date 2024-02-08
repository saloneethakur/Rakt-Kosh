package com.raktKosh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloodBankUpdateModel 
{
	private String address;
	private String phone;
	private Boolean active_status;
}
