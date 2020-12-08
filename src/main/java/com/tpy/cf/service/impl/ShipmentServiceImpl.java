package com.tpy.cf.service.impl;

import com.tpy.cf.api.Result;
import com.tpy.cf.dao.cfmisMapper.ShipmentMapper;
import com.tpy.cf.service.ShipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    private ShipmentMapper shipmentMapper;

    @Override
    public List<Map> findCustomerOrderInfo(Map map) {
        return shipmentMapper.findCustomerOrderInfo(map);
    }

    @Override
    public List<Map> findShipmentOrderByType(String type) {
        return shipmentMapper.findShipmentOrderByType(type);
    }

    @Override
    public List<Map> findDetailByHeaderGUID(String header_guid) {
        return shipmentMapper.findDetailByHeaderGUID(header_guid);
    }

    @Override
    @Transactional(value="cfmisTransactionManager")
    public Result addShipmentOrder(List<Map> list) {

        for (Map map : list){
            map.put("customer_GUID","CEBEDE0C-7F80-44B8-85E5-AC4B013578BD");
            map.put("update_man","唐鹏翼");
        }

        Result result = checkShipmentOrder(list);
        if(result != null)
            return result;

        String shipmentPlanFabricHdrGUID = shipmentMapper.addShipmentHeader(list.get(0));
        if(shipmentPlanFabricHdrGUID == null){
            return Result.failed("生成落单通知单失败");
        }

        for (Map map : list){
            map.put("shipmentPlanFabricHdrGUID",shipmentPlanFabricHdrGUID);
            shipmentMapper.addShipmentDetail(map);
        }

        Map map = list.get(0);
        map.put("status","开单");
        int i = shipmentMapper.modifyCustomerOrderType(map);
        log.info("修改客户订单状态===="+i);

        return Result.success(shipmentPlanFabricHdrGUID);
    }

    private Result checkShipmentOrder(List<Map> list){
        if(list.size() == 0){
            return Result.failed("未选择订单信息");
        }

        String customer_GUID = (String) list.get(0).get("customer_GUID");

        if("".equals(customer_GUID) || customer_GUID == null){
            return Result.failed("工厂客户映射表没有此信息，请添加映射关系在进行此操作！");
        }

        for (Map map : list){
            if(!customer_GUID.equals(map.get("customer_GUID"))){
                return Result.failed("存在客户不一致，无法合并同一订单");
            }
        }
        return null;
    }

}
