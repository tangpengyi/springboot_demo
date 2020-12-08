package com.tpy.cf.service;

import com.github.pagehelper.PageInfo;
import com.tpy.cf.api.Result;

import java.util.Map;

public interface SalesPlanOrderService {

    public Result add(Map map);

    public Result modify(Map map);

    public PageInfo<Map> list(Map map);

    public Result delete(int id);

    public Map get(Integer id);


}
