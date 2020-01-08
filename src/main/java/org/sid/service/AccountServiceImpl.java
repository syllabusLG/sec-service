package org.sid.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.sid.dao.AppRoleRepository;
import org.sid.dao.AppUserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	

	private AppUserRepository userRepository;

	private AppRoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	

	public AccountServiceImpl(AppUserRepository userRepository, AppRoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public AppUser saveUser(String username, String password, String confirmedPassword) {
		AppUser user = userRepository.findByUsername(username);
		if(user != null) throw new RuntimeException("User already exists");
		if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password!");	
		AppUser appUser = new AppUser();
		appUser.setUsername(username);
		appUser.setActivated(true);
		appUser.setPassword(bCryptPasswordEncoder.encode(password));
		//addRoleToUser(username, "USER");
		appUser.getRole().add(roleRepository.findByRoleName("USER"));
		userRepository.save(appUser);
		return appUser;
	}

	@Override
	public AppRole saveRole(AppRole role) {
		return roleRepository.save(role);
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppUser user = userRepository.findByUsername(username);
		AppRole role = roleRepository.findByRoleName(roleName);
		if (user != null && role != null) {
			user.getRole().add(role);
		}
		

	}

}
