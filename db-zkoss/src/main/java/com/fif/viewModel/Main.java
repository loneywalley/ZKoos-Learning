package com.fif.viewModel;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Main {
    public static void main(String[] args){
        String user = passwordEncoder().encode("user");
        System.out.println(user);
        String admin = passwordEncoder().encode("admin");
        System.out.println(admin);
    }

    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
