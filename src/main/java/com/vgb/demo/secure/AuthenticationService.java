package com.vgb.demo.secure;


import com.vgb.demo.secure.data.UserAccount;
import com.vgb.demo.secure.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        //load user account details from repository
        final UserAccount userAccount = accountRepository.lookupByUserName(userName);

        if(userAccount == null){
            throw new UsernameNotFoundException("user not found : " + userName);
        }
        System.err.println("#### found and loaded user account details for userName: " + userAccount.toString());
        //create a spring security user from the account details
        final User user = createUser(userAccount);

        return user;
    }

    private User createUser(UserAccount userAccount) {
        return new User(userAccount.getUsername(), userAccount.getPassword(), createAuthorities(userAccount));
    }

    private Collection<GrantedAuthority> createAuthorities(UserAccount userAccount) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+userAccount.getRole()));
        return  authorities;
    }
}