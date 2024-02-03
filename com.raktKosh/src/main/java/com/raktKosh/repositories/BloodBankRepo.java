package com.raktKosh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raktKosh.entities.BloodBank;

@Repository
public interface BloodBankRepo extends JpaRepository<BloodBank, Long> {

}
