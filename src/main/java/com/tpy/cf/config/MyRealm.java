package com.tpy.cf.config;

import com.tpy.cf.dao.tpyMapper.UserMapper;
import com.tpy.cf.entity.UserVO;
import com.tpy.cf.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
public class MyRealm extends AuthorizingRealm {

    private static String TOKEN = "";
    private static boolean isLogin = false;

    private final String PASSWORD = "201201030120120301";

    @Autowired
    private UserMapper userMapper;

    /**
     * 执行授权逻辑
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        log.info("执行授权");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Integer userId = JwtUtils.getUserId(TOKEN);

        List<String> permissions = userMapper.selectPermissionByUserId(userId);
        for (String permission : permissions){
            info.addStringPermission(permission);
        }

        return info;
    }

    /**
     * 执行认证逻辑
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken user =  (UsernamePasswordToken) token;
        UserVO login = userMapper.login(user.getUsername());

        if(isLogin){
            isLogin = false;
            return new SimpleAuthenticationInfo(login,PASSWORD,"");
        }

        if(login == null)
            return null;

        if(!StringUtils.pathEquals(login.getName(),user.getUsername())){
            return null;
        }

        //判断密码是否正确
        return new SimpleAuthenticationInfo(login,login.getPassword(),"");
    }


    public static void setToken(String token){
        MyRealm.TOKEN = token;
    }
    public static void setNotLogin(){
        MyRealm.isLogin = true;
    }
}
