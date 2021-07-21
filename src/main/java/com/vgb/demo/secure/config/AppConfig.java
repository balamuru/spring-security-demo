package com.vgb.demo.secure.config;

import com.vgb.demo.secure.data.UserAccount;
import com.vgb.demo.secure.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Bean
    AccountRepository accountRepository() {
        AccountRepository accountRepository = new AccountRepository();
        accountRepository.create(new UserAccount("user", passwordEncoder.encode("passwd"), "USER"));
        accountRepository.create(new UserAccount("admin", passwordEncoder.encode("passwd"), "ADMIN"));
        return accountRepository;
    }
}
