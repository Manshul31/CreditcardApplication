package com.mifinity.CreditCardApplication.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mifinity.CreditCardApplication.dao.IUserRepository;
import com.mifinity.CreditCardApplication.model.Role;
import com.mifinity.CreditCardApplication.model.User;

/**
 * UserDetailsServiceImpl is a class that extends the UserDetailsService 
 * and overrides all the methods present in it. 
 * 
 * @author Manshul
 * @version 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
    
}
