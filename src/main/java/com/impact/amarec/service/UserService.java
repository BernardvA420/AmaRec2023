package com.impact.amarec.service;


import com.impact.amarec.Dto.UserDto;
import com.impact.amarec.entity.User;

import java.util.List;


public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    User findUserByName(String name);

    List<UserDto> findAllUsers();
}