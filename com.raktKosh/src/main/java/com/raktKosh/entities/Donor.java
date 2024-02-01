package com.raktKosh.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.sym.Name;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donor_id")
    private Long donorId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String gender;

    
    
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Column(name = "bloodgroup", nullable = false)
    private String bloodGroup;

    @Column(nullable = false,unique = true)
    private String aadharCard;

    
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastDonateDate;

    @Column(nullable = false)
    private String type;

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
}

