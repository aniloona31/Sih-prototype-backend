package com.example.smartindiahackathon.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.smartindiahackathon.Dto.CustomUserDetails;
import com.example.smartindiahackathon.Model.User;
import com.example.smartindiahackathon.Repository.UserRepository;






@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		user.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
		
		return new CustomUserDetails(user.get());
	}
	
	
}
