package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;

@Component
public class CustomerInit implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public void run(String... arg0) throws Exception {
		userRepo.save(new User("mano", new BCryptPasswordEncoder().encode("mano"), true));
		userRepo.save(new User("test", new BCryptPasswordEncoder().encode("test"), true));

		roleRepo.save(new Role("mano", "ADMIN"));
		roleRepo.save(new Role("test", "USER"));
	}

}
