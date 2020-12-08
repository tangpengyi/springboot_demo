package com.tpy.cf.controller;

import com.tpy.cf.annototion.ResponseResult;
import com.tpy.cf.api.Result;
import com.tpy.cf.dao.tpyMapper.UserMapper;
import com.tpy.cf.entity.User;
import com.tpy.cf.entity.UserVO;
import com.tpy.cf.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "用户接口",tags = "",description = "用户增删修查")
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value="所有用户",httpMethod = "GET",notes = "")
    @GetMapping("list")
    @ResponseResult
    public List<UserVO> list(){
        return userMapper.getAllUser();
    }

    @PostMapping("login")
    @ResponseResult
    @ApiOperation(value="登录",httpMethod = "POST",notes = "")
    public String login(@ApiParam(name="User",value="传入json格式",required=true) @RequestBody @Valid User user){

        List<UserVO> users = userMapper.login(user.getName());
        List<UserVO> collect = users.stream()
                .filter(e -> {
                    return StringUtils.pathEquals(user.getPassword(), e.getPassword());
                })
                .collect(Collectors.toList());
        if(collect.size() != 1){
            return "登录失败";
        }

        return JwtUtils.getJavaToken();
    }

    @PostMapping("/add")
    @ResponseResult
    @ApiOperation(value="新增用户",httpMethod = "POST",notes = "")
    public UserVO add(@ApiParam(name="User",value="传入json格式",required=true) @RequestBody @Valid User user){
        int add = userMapper.add(user);
        if(add == 1){
            return userMapper.get(user.getId());
        }else{
            try {
                throw new Exception("添加失败");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @GetMapping("/delete/{id}")
    @ApiOperation(value="删除用户",httpMethod = "GET",notes = "")
    public Result delete(@ApiParam(name="id",value="id",type = "INTEGER", required=true) @NotEmpty(message = "参数不能为空") @PathVariable("id") Integer id){
        return userMapper.delete(id) == 0 ? Result.failed("删除失败"):Result.success("删除成功");
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value="查询用户",httpMethod = "GET",notes = "")
    public UserVO get(@NotEmpty(message = "参数不能为空") @PathVariable("id") Integer id){
        return userMapper.get(id);
    }

}
