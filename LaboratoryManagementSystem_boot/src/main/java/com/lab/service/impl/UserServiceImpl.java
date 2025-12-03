package com.lab.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lab.dto.LoginRequest;
import com.lab.dto.LoginResponse;
import com.lab.dto.UserDTO;
import com.lab.entity.Role;
import com.lab.entity.RoleType;
import com.lab.entity.User;
import com.lab.repository.RoleRepository;
import com.lab.repository.UserRepository;
import com.lab.service.UserService;
import com.lab.util.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsServiceImpl UserDetailsServiceImpl ;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public UserDTO register(UserDTO dto) {
		
		if (userRepository.existsByUsername(dto.getUsername()))
			throw new RuntimeException("Username already exists");

		if (userRepository.existsByEmail(dto.getEmail()))
			throw new RuntimeException("Email already exists");

	
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());

		
		user.setPassword(passwordEncoder.encode(dto.getPassword()));

		Set<Role> roles = new HashSet<>();

	
		if (dto.getRoles() != null && !dto.getRoles().isEmpty()) {
			for (String roleName : dto.getRoles()) {
				RoleType roleType = RoleType.valueOf(roleName); // Convert string to enum
				Role role = roleRepository.findByName(roleType)
						.orElseGet(() -> roleRepository.save(new Role(null, roleType))); // Create role if not exists
				roles.add(role);
			}
		} else {
			// Default role
			Role role = roleRepository.findByName(RoleType.ROLE_PATIENT)
					.orElseGet(() -> roleRepository.save(new Role(null, RoleType.ROLE_PATIENT)));
			roles.add(role);
		}

		user.setRoles(roles);

		// Save user in database
		User savedUser = userRepository.save(user);

		// Prepare response DTO (password is intentionally omitted for security)
		UserDTO response = new UserDTO();
		response.setId(savedUser.getId());
		response.setUsername(savedUser.getUsername());
		response.setEmail(savedUser.getEmail());
		response.setRoles(savedUser.getRoles().stream().map(r -> r.getName().name()) // Convert enum to string
				.collect(Collectors.toSet()));

		return response;
	}

	@Override
	public LoginResponse login(LoginRequest request) {

		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			// Load user from DB
			User user = userRepository.findByUsername(request.getUsername())
					.orElseThrow(() -> new RuntimeException("User not found"));

			// Generate JWT token
			String token = jwtUtil.generateToken(user.getUsername());

			
			
		

	        // 4️⃣ Extract roles from ENUM
	        Set<Role> roles = user.getRoles();
	        String roleName = null;

	        if (roles != null && !roles.isEmpty()) {
	        
	            roleName = roles.iterator().next().getName().name();
	        }

	        System.out.println("Extracted Role = " + roleName);
	        
	     // Prepare login response
	     			LoginResponse response = new LoginResponse();
	     			response.setToken(token);
	     			response.setUsername(user.getUsername());
			
	        response.setRoles(roleName);

			return response;

		} catch (BadCredentialsException e) {
			throw new RuntimeException("Invalid username or password");
		} catch (UsernameNotFoundException e) {
			throw new RuntimeException("User not found");
		} catch (Exception e) {
			throw new RuntimeException("Login failed: " + e.getMessage());
		}

	}

	@Override
	public String getRoleByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
