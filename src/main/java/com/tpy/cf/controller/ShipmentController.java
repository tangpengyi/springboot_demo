package com.tpy.cf.controller;

import com.tpy.cf.annototion.ResponseResult;
import com.tpy.cf.api.Result;
import com.tpy.cf.service.ShipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("落单通知单")
@RestController
@RequestMapping("shipment")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @ApiOperation("查询客户订单信息")
    @GetMapping("findCustomerOrderInfo")
    @ResponseResult
    public List<Map> findCustomerOrderInfo(String sales_order_no,String main_factory, String from, String to) throws ParseException {
        Map map = new HashMap();
        map.put("sales_order_no",sales_order_no);
        map.put("main_factory",main_factory);
        map.put("from",from);
        map.put("to",to);
        return shipmentService.findCustomerOrderInfo(map);
    }

    @ApiOperation("查询落单通知根据类型")
    @GetMapping("findShipmentOrderByType")
    @ResponseResult
    public List<Map> findShipmentOrderByType(@RequestParam String type) {
        return shipmentService.findShipmentOrderByType(type);
    }


    @ApiOperation("查询明细根据header_GUID")
    @GetMapping("findDetailByHeaderGUID")
    @ResponseResult
    public List<Map> findDetailByHeaderGUID(@RequestParam String header_guid) {
        return shipmentService.findDetailByHeaderGUID(header_guid);
    }

    @ApiOperation("转落单通知")
    @PostMapping("addShipmentOrder")
    public Result addShipmentOrder(@RequestBody List<Map> list) {
        return shipmentService.addShipmentOrder(list);
    }
}
