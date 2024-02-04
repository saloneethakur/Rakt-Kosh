package com.raktKosh.utils;

import java.util.HashMap;

public interface SystemConstants 
{
	String GENDER[] = {
			"Male" , "Female" , "Others"
	};
	
	String BLOODGROUP[]=
		{
				"A+","A-","B+","B-","AB+","AB-","O+","O-"
		};
	
	
	String PAYMENT_MODES[] = {
			"Cash" , "UPI" , "Card"
	};
	
	
	
	static HashMap<String, String[]>  getConstants() 
	{
		HashMap<String, String []> data = new HashMap<>();
		data.put("gender", GENDER);
		
		data.put("payment_modes", PAYMENT_MODES);
		data.put("bloodGroup", BLOODGROUP);
		
		return data;
	}
}
