package com.tpy.cf.dao.tpyMapper;

import com.tpy.cf.entity.User;
import com.tpy.cf.entity.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    public UserVO get(Integer id);

    public List<UserVO> getAllUser();

    public UserVO login(String name);

    public int add(User user);

    public int delete(Integer id);

    public int update(User user);

    public List<String> selectRoleByUserId(Integer userId);

    public List<String> selectPermissionByUserId(Integer userId);
}
