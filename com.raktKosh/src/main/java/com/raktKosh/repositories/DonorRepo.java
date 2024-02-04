package com.raktKosh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raktKosh.entities.Donor;

@Repository
public interface DonorRepo extends JpaRepository<Donor, Long> {

}
