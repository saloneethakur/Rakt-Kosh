package com.raktKosh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.raktKosh.entities.BloodBank;
import com.raktKosh.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonorModel {

    
    private String name;
    private String phone;
    private String address;
    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private String bloodGroup;
    private String aadharCard;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastDonateDate;

    private String type;
    private String email;
	private String password;
}
