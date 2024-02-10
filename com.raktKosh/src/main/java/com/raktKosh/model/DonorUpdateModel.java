package com.raktKosh.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonorUpdateModel 
{
	private String phone;
	private String address;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastDonateDate;
	
	private Boolean active_status;
}
