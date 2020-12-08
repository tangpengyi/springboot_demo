package com.tpy.cf.service;

import com.tpy.cf.entity.User;

public interface UserService {

    public String login(User user);
    public void login(Integer userId);

}
