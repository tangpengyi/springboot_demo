package com.tpy.cf.service;

import com.tpy.cf.annototion.ResponseResult;
import com.tpy.cf.api.Result;

import java.util.List;
import java.util.Map;

public interface ShipmentService {

    public List<Map> findCustomerOrderInfo(Map map);

    public List<Map> findShipmentOrderByType(String type);

    public List<Map> findDetailByHeaderGUID(String header_guid);

    public Result addShipmentOrder(List<Map> List);

}
