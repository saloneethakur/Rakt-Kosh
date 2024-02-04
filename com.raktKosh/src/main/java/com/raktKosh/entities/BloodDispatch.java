package com.raktKosh.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

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
@Entity
@Data

@NoArgsConstructor
@AllArgsConstructor

@Table(name="BloodDispatch")
public class BloodDispatch {
	@Id

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="blooddispatch_id")
	private Long BloodDispatchId;
	

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
	
	@Column(name="hospital",nullable=false)
	private String hospital;
	
	 @Column(name = "docs")  
	    private String docs;
	
	@Column(name="quantity",nullable=false)
	private Integer quantity;
	
	 @ManyToOne
    @JoinColumn(name = "bloodbank", nullable = false)
    private BloodBank bloodBank;
	 
}