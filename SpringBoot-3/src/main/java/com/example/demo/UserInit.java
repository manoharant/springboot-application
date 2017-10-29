package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Component
public class UserInit implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... arg0) throws Exception {
		User user = new User("mano", new BCryptPasswordEncoder().encode("mano"), true);
		User admin = new User("admin", new BCryptPasswordEncoder().encode("admin"), true);
		userRepository.save(user);
		userRepository.save(admin);

		Role userRole = new Role("user", "mano");
		Role adminRole = new Role("admin", "admin");
		roleRepository.save(userRole);
		roleRepository.save(adminRole);

	}

}
