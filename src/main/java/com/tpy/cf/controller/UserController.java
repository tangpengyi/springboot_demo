package com.tpy.cf.controller;

import com.tpy.cf.annototion.ResponseResult;
import com.tpy.cf.api.Result;
import com.tpy.cf.dao.tpyMapper.UserMapper;
import com.tpy.cf.entity.User;
import com.tpy.cf.entity.UserVO;
import com.tpy.cf.service.UserService;
import com.tpy.cf.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Api(value = "用户接口",tags = "",description = "用户增删修查")
@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

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
        return userService.login(user);
    }

    @RequiresPermissions("user:add")
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

    @RequiresPermissions("user:delete")
    @GetMapping("/delete/{id}")
    @ApiOperation(value="删除用户",httpMethod = "GET",notes = "")
    public Result delete(@ApiParam(name="id",value="id",type = "INTEGER", required=true) @NotEmpty(message = "参数不能为空") @PathVariable("id") Integer id){
        return userMapper.delete(id) == 0 ? Result.failed("删除失败"):Result.success("删除成功");
    }

    @RequiresPermissions("user:query")
    @GetMapping("/get/{id}")
    @ApiOperation(value="查询用户",httpMethod = "GET",notes = "")
    @ResponseResult
    public UserVO get(@NotEmpty(message = "参数不能为空") @PathVariable("id") Integer id){
        return userMapper.get(id);
    }

    @RequiresPermissions("user:modify")
    @PostMapping("/modify")
    @ApiOperation(value="修改用户",httpMethod = "POST",notes = "")
    @ResponseResult
    public String modify(@ApiParam(name="User",value="传入json格式",required=true) @RequestBody @Valid User user){
        int update = userMapper.update(user);
        if(update == 0)
            try {
                throw new Exception("修改失败");
            } catch (Exception e) {
                e.printStackTrace();
            }
        return "修改成功";
    }

}
