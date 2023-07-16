package com.decagon.swisspay.infrastructure.configuration.security;

import com.decagon.swisspay.infrastructure.error_handler.CustomNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserDetails {
    public static String getLoggedInUserDetails(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!(principal instanceof org.springframework.security.core.userdetails.UserDetails)) {
            throw new CustomNotFoundException("user not found");
        }
        return ((org.springframework.security.core.userdetails.UserDetails)principal).getUsername();
    }
}
