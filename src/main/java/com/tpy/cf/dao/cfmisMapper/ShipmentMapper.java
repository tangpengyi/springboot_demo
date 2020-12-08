package com.tpy.cf.dao.cfmisMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ShipmentMapper {

    //        String order_no, String factory, String startTime, String endTime
    public List<Map> findCustomerOrderInfo(Map map);

    public List<Map> findShipmentOrderByType(String type);

    public List<Map> findDetailByHeaderGUID(String header_guid);

    public int updateCustomerOrderType(Map map);

    public String addShipmentHeader(Map map);

    public int addShipmentDetail(Map map);

    public int modifyCustomerOrderType(Map map);
}
