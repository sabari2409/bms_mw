package com.scaler.bms.services.interfaces;

import com.scaler.bms.entity.Users;

import java.util.Optional;

public interface UserService {

    Users getUser(Integer userId);
}
