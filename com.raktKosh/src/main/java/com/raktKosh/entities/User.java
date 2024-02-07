package com.raktKosh.entities;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.raktKosh.model.UpdatePasswordModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails
{
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "user_id")
    private Long userId;
	
	@NotEmpty
	@Email
    @Column(nullable = false, unique = true)
    private String email;

	@NotEmpty
    @Column(nullable = false)
    private String password;

	@NotEmpty
    @Column(nullable = false)
    private String role;

    @Column(name = "active_status", nullable = false)
    private boolean activeStatus;
    
    
	
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.getRole());
		return Arrays.asList(authority);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public User(String email, String password, String role, boolean activeStatus) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
		this.activeStatus = activeStatus;
	}


	public void update(UpdatePasswordModel model) 
	{
		if(model.getNewPassword() != null)
			this.password = passwordEncoder.encode(model.getNewPassword());
	}
	
	
}