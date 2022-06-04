package com.example.authservice.sec.service;


import com.example.authservice.sec.entities.AppRole;
import com.example.authservice.sec.entities.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    AppUser loadUserByUserName(String username);
    List<AppUser> listUsers();
}
