package com.raktKosh.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raktKosh.entities.BloodBank;
import com.raktKosh.entities.Donor;
import com.raktKosh.entities.User;

@Repository
public interface DonorRepo extends JpaRepository<Donor, Long> {

	List<Donor> findAllByDonorId(Long donorId);
	Optional<Donor> findByUser(User user);
}
