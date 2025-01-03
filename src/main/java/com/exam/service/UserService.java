package com.exam.service;

import com.exam.models.User;
import com.exam.models.UserRole;

import java.util.Set;

public interface UserService {
    //creating a new user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
    //geting the user
    public User getUser(String username);
    //deleting the user
    public void deleteUser(Long userId);
}
