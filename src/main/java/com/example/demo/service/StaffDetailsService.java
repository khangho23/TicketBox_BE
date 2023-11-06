package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StaffDao;
import com.example.demo.entity.Staff;
import com.example.demo.enums.Role;

@Service
public class StaffDetailsService implements UserDetailsService{

	@Autowired
	private StaffDao staffDao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Staff> staff = staffDao.findByEmail(email);

		if(staff.isEmpty()) {
			throw new UsernameNotFoundException("Không tồn tại người dùng " + email);
		}

		String role = String.valueOf(Role.getRoleByValue(staff.get().getRole()));

		return User.withUsername(staff.get().getEmail())
                .password(staff.get().getPassword()).roles(role)
                .build();
	}
}