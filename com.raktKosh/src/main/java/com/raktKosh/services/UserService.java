package com.raktKosh.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.raktKosh.entities.User;
import com.raktKosh.repositories.UserRepo;


@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	public User getById(Long userid) {
		return userRepo.findById(userid).get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		UserDetails obj =  userRepo.findByEmail(username).get();
		return obj;
	}


	public User saveUser(String email, String password,String role) 
	{
		User user = new User(email, password, role, false);
		user = userRepo.save(user);
		return user;
	}
	public Optional<User> findByEmail(String email) 
	{
		return userRepo.findByEmail(email);
	}
	
	public void update(User user) {
		userRepo.save(user);		
	}

}
