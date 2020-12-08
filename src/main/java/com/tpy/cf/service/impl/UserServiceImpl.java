package com.tpy.cf.service.impl;

import com.tpy.cf.config.MyRealm;
import com.tpy.cf.dao.tpyMapper.UserMapper;
import com.tpy.cf.entity.User;
import com.tpy.cf.entity.UserVO;
import com.tpy.cf.service.UserService;
import com.tpy.cf.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private final String PASSWORD = "201201030120120301";

    @Override
    public String login(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
        try{
            subject.login(token);
        }catch (UnknownAccountException e){
            return "用户不存在";
        }catch (IncorrectCredentialsException e){
            return "密码错误";
        }
        UserVO loginUser = (UserVO) subject.getPrincipal();
        return JwtUtils.getJavaToken(loginUser.getId());
    }

    @Override
    public void login(Integer userId) {
        Subject subject = SecurityUtils.getSubject();
        UserVO userVO = userMapper.get(userId);

        MyRealm.setNotLogin();
        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getName(), PASSWORD);
        try{
            subject.login(token);
        }catch (Exception e){
            log.error(e.getMessage());
            try {
                throw new Exception("系统异常");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
