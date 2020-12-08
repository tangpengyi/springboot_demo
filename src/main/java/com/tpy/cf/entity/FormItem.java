package com.tpy.cf.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel("表单列名")
public class FormItem {

    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("列名类型")
    private String type;
    @ApiModelProperty("列名")
    private String item_name;
    @ApiModelProperty("说明")
    private String description;
    @ApiModelProperty("排序")
    private int sort_order;
    @ApiModelProperty("是否可见")
    private int is_visible;
    @ApiModelProperty("是否可用")
    private byte is_enable;
    @ApiModelProperty("是否必须")
    private byte is_required;
    @ApiModelProperty("值类型")
    private String value_type;
    @ApiModelProperty("默认值")
    private String default_value;

    private int create_user;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_time;
    private int modify_user;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modify_time;

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

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSort_order() {
        return sort_order;
    }

    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
    }

    public int getIs_visible() {
        return is_visible;
    }

    public void setIs_visible(int is_visible) {
        this.is_visible = is_visible;
    }

    public byte getIs_enable() {
        return is_enable;
    }

    public void setIs_enable(byte is_enable) {
        this.is_enable = is_enable;
    }

    public byte getIs_required() {
        return is_required;
    }

    public void setIs_required(byte is_required) {
        this.is_required = is_required;
    }

    public String getValue_type() {
        return value_type;
    }

    public void setValue_type(String value_type) {
        this.value_type = value_type;
    }

    public String getDefault_value() {
        return default_value;
    }

    public void setDefault_value(String default_value) {
        this.default_value = default_value;
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
