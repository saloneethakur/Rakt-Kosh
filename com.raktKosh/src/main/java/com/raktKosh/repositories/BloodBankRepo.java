package com.raktKosh.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raktKosh.entities.BloodBank;
import com.raktKosh.entities.User;

@Repository
public interface BloodBankRepo extends JpaRepository<BloodBank, Long> 
{
	Optional<BloodBank> findByUser(User user);
	List<BloodBank> findAllByBankId(Long bankId);
}
