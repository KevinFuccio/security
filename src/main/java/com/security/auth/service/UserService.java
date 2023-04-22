package com.security.auth.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.security.auth.entity.ERole;
import com.security.auth.entity.Role;
import com.security.auth.repository.RoleRepository;
import com.security.auth.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	
	public void changePermissions(String s,ERole roles) {
		Set<Role> role = new HashSet<Role>();
		role.add(roleRepository.findByRoleName(roles).get());
		com.security.auth.entity.User u = userRepository.findByEmail(s).get();
		u.setRoles(role);
		userRepository.save(u);
	}
}
