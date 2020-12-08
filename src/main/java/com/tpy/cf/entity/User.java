package com.tpy.cf.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value="用户类",description="请求参数类" )
public class User {

    private int id;

    @NotEmpty(message = "不能为空")
    @ApiModelProperty(value = "姓名",example="张三")
    private String name;

    @Range(min = 0, max = 50, message = "年龄不能少于0")
    @ApiModelProperty(value = "年龄",example="23")
    private Integer age;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6,max = 20,message = "密码不能少于六位")
    @ApiModelProperty(value = "密码",example="123456")
    private String password;

}
