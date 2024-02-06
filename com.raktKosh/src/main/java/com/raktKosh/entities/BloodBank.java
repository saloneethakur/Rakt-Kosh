package com.raktKosh.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BloodBank")
public class BloodBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Long bankId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false , unique = true)
    private String phone;

    @Column(name = "active_status", nullable = false)
    private boolean activeStatus ;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    @JsonIgnore
    private User user;
    
    @ManyToMany(mappedBy="banks")
 	private Set<Donor> donors = new HashSet<Donor>();

	public BloodBank(String name, String address, String phone, boolean activeStatus, User user) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.activeStatus = activeStatus;
		this.user = user;
	}
    
    
}