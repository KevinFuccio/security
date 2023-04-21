package com.security.auth.service;

import com.security.auth.payload.LoginDto;
import com.security.auth.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
