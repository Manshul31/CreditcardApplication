package com.mifinity.CreditCardApplication.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.mifinity.CreditCardApplication.model.Role;
import com.mifinity.CreditCardApplication.model.User;
import com.mifinity.CreditCardApplication.util.Constants;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
    private IUserRepository userRepository;
  
    @Autowired
    private IRoleRepository roleRepository;
    
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		//Create Roles
		Role adminRole = roleRepository.findByName(Constants.ROLE_ADMIN);
        if (adminRole == null) {
            adminRole = new Role(Constants.ROLE_ADMIN);
            roleRepository.save(adminRole);
        }

        Role userRole = roleRepository.findByName(Constants.ROLE_USER);
        if (userRole == null) {
            userRole = new Role(Constants.ROLE_USER);
            roleRepository.save(userRole);
        }
        
        //Create admin
        User user = userRepository.findByUsername(Constants.ADMIN_USER);
        if (user == null) {
            user = new User("admin", bCryptPasswordEncoder.encode(Constants.ADMIN_PASSWORD), adminRole);
            userRepository.save(user);
        }
		
	}
	
}
