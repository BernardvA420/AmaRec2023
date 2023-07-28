package com.springboot.registrationlogin.service;
import com.springboot.registrationlogin.Model.User;
import com.springboot.registrationlogin.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
