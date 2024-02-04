package com.raktKosh.entities;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="DispatchCollection")
public class DispatchCollection {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "dispatchCollection_id")
	    private Long dispatchCollectionId;
	 

	    @ManyToOne
	    @JoinColumn(name = "dispatch", nullable = false)
	    private BloodDispatch dispatch;
	    
	    @ManyToOne
	    @JoinColumn(name = "bloodcollection", nullable = false)
	    private BloodCollection bloodCollection;
	    	
}