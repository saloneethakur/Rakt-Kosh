package com.raktKosh.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.raktKosh.model.DonorUpdateModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Donor")
public class Donor {
	//Validation Remaining

    public Donor(String name, String phone, String address, String gender, LocalDate dob, String bloodGroup,
			String aadharCard, LocalDate lastDonateDate, String type, User user,Boolean active_status,LocalDateTime reg) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
		this.dob = dob;
		this.bloodGroup = bloodGroup;
		this.aadharCard = aadharCard;
		this.lastDonateDate = lastDonateDate;
		this.type = type;
		this.user = user;
		this.active_status = active_status;
		this.RegDate = reg;
	}
    
    @JsonIgnore
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donor_id")
    private Long donorId;

//	@NotEmpty
    @Column(nullable = false)
    private String name;
	
	//@Size(min = 10,max=10,message = "Invalid Phone Number")
    @Column(nullable = false,unique = true)
    private String phone;

	
    @Column(nullable = false)
    private String address;

    //@NotEmpty
    @Column(nullable = false)
    private String gender;

    
    
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Column(name = "bloodgroup", nullable = false)
    private String bloodGroup;

   // @Size(min = 12,max = 12,message = "Invalid Adhar Number")
    @Column(nullable = false,unique = true)
    private String aadharCard;

    
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastDonateDate;

    @JsonIgnore
    @Column(nullable = false)
    private String type;

    @Column (nullable = false)
    private Boolean active_status;
    
    @Column(name = "Registration_Date",nullable = false )
    @JsonFormat(pattern ="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime RegDate;
    

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;
    
    @ManyToMany
    @JoinTable(name="donorBank",
    joinColumns = {
    		@JoinColumn(name="donorId",referencedColumnName  ="donor_id")},
    inverseJoinColumns = {
    		@JoinColumn(name="bankId",referencedColumnName  ="bank_id")
    })
    private Set<BloodBank> banks = new HashSet<BloodBank>();

	public void update(DonorUpdateModel model) 
	{
		if(model.getActive_status() != null)
			this.active_status = model.getActive_status();
		if(model.getAddress() != null)
			this.address = model.getAddress();
		if(model.getPhone() != null)
			this.getPhone();
		if(model.getLastDonateDate() != null)
			this.lastDonateDate = model.getLastDonateDate();
	}
}

