package com.tpy.cf.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel("明细表")
public class FormDetail {

    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("主表id")
    private int header_id;
    @ApiModelProperty("列名id")
    private int item_id;
    @ApiModelProperty("列名")
    private String item_name;
    @ApiModelProperty("列值")
    private String item_value;
    @ApiModelProperty("备注")
    private String remark;

    private int create_user;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_time;
    private int modify_user;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modify_time;


    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeader_id() {
        return header_id;
    }

    public void setHeader_id(int header_id) {
        this.header_id = header_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_value() {
        return item_value;
    }

    public void setItem_value(String item_value) {
        this.item_value = item_value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCreate_user() {
        return create_user;
    }

    public void setCreate_user(int create_user) {
        this.create_user = create_user;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getModify_user() {
        return modify_user;
    }

    public void setModify_user(int modify_user) {
        this.modify_user = modify_user;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }
}
