package com.exam.service.impl;

import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local = this.userRepository.findByUsername(user.getUsername());
        if(local!=null) {                                     //here we are checing whether the user to be created already exists or not
            System.out.println("user already exists");        //if user already exists the throw exception here
            throw new Exception("User already exists with username: " + user.getUsername());
        }
        else {
            for(UserRole ur:userRoles)                      //firstly saving the user roles in database
            {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);          //assigning given roles to user
            local=this.userRepository.save(user);           //saving the new user to database
        }
        return local;
    }

    //getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}
