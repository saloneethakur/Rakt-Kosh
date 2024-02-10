package com.raktKosh.services;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.raktKosh.entities.User;
import com.raktKosh.model.PasswordUpdateModel;
import com.raktKosh.repositories.UserRepo;


@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
		User user = new User(email, password, role, true);
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

	public boolean changePassword(PasswordUpdateModel model, Principal connectedUser) 
	{
		User user = (User)((UsernamePasswordAuthenticationToken)connectedUser).getPrincipal();
		
		if(!passwordEncoder.matches(model.getOldPassword(),user.getPassword() ))
		{
			return false;
		}
		if(!model.getNewPassword().equals(model.getConfirmPassword()))
		{
			return false;
		}
		
		
		user.setPassword(passwordEncoder.encode(model.getNewPassword()));
		
		userRepo.save(user);
		return true;
	}

	

}
