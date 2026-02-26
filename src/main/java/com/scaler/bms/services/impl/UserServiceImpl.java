package com.scaler.bms.services.impl;

import com.scaler.bms.entity.Users;
import com.scaler.bms.repository.UserRepository;
import com.scaler.bms.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Users getUser(Integer userId) {
        Optional<Users> userDetails = this.userRepository.findById(userId);
        return userDetails.orElse(null);
    }
}
