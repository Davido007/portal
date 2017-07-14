package io.pivotal.microservices.Security;

import io.pivotal.microservices.accounts.AccountsController;
import io.pivotal.microservices.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by DPLICHTA on 4/13/2017.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        System.out.println("nnnnnnnnnnnnnnn" + login);
        log.debug("Authenticating {}", login);

        io.pivotal.microservices.accounts.User user = userRepository.findByLogin(login);
        System.out.println(user);
        if (user == null) {
            System.out.println("zzz");
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        } else if (!user.getEnabled()) {
            throw new userService.exceptions.UserNotEnabledException("User " + login + " was not enabled");
        }
        System.out.println("xxx");
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for (AccountsController.Authority authority : user.getAuthorities()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }

        //  String encodedPassword = passwordEncoder.encode(user.getPassword());
        String encodedPassword = user.getPassword();
//        passwordEncoder
        return new org.springframework.security.core.userdetails.User(login, encodedPassword,
                grantedAuthorities);
    }
}

/*
@Service("userDetailsService")
@Transactional
//public class MyUserDetailsService {
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    //
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(
                    "No user found with username: "+ email);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return  new org.springframework.security.core.userdetails.User
                (user.getEmail(),
                        user.getPassword().toLowerCase(), enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked,
                        getAuthorities(user.getRoles()));
    }

    private static List<GrantedAuthority> getAuthorities (Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}*/
