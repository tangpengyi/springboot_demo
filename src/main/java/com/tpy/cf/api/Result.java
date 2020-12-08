package com.tpy.cf.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "响应结果集",description = "响应返回类")
public class Result<T> implements Serializable {

    @NotEmpty(message = "状态码不能为空")
    @ApiModelProperty(example = "200")
    private int code;

    @NotEmpty(message = "返回message不能为空")
    @ApiModelProperty(example = "success")
    private String msg;

    // 返回数据
    private T data;

    public static Result success(String msg,Object object){
        Result result = new Result();
        result.setCode(0);
        result.setMsg(msg);
        result.setData(object);
        return result;
    }
    public static Result success(Object object){
        return success("success",object);
    }

    public static Result failed(String msg){
        Result result = new Result();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }
    public static Result failed(){
        return failed("操作失败");
    }

}
