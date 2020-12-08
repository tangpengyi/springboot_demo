package com.tpy.cf.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@ApiModel("表单header")
public class FormHeader {

    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("表单类型")
    private String type;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("订单编号")
    private String order_no;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("备注")
    private String remark;


    private int create_user;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_time;
    private int modify_user;
    private Date modify_time;

    private List<FormDetail> formDetial;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<FormDetail> getFormDetial() {
        return formDetial;
    }

    public void setFormDetial(List<FormDetail> formDetial) {
        this.formDetial = formDetial;
    }
}
