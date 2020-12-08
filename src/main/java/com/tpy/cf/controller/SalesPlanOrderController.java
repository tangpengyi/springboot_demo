package com.tpy.cf.controller;

import com.github.pagehelper.PageInfo;
import com.tpy.cf.api.Result;
import com.tpy.cf.service.SalesPlanOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api("备货计划")
@RequestMapping("/dev/sales")
public class SalesPlanOrderController {

    @Autowired
    private SalesPlanOrderService salesPlanOrderService;

    @ApiOperation("修改")
    @PutMapping("/stock_plan_order")
    public Result modify(@RequestBody Map map){
        map.put("modity_user","陈昌");
        map.put("accept_user","陈昌");
        return salesPlanOrderService.modify(map);
    }

    @ApiOperation("指定id查询")
    @GetMapping("/stock_plan_order/{id}")
    public Map get(@PathVariable("id") Integer id){
        return salesPlanOrderService.get(id);
    }

    @ApiOperation("删除")
    @DeleteMapping("/stock_plan_order/{id}")
    public Result delete(@PathVariable("id") Integer id){
        return salesPlanOrderService.delete(id);
    }

    @ApiOperation("添加")
    @PostMapping("/stock_plan_order")
    public Result add(@RequestBody Map map){
        map.put("modity_user","陈昌");
        map.put("accept_user","陈昌");
        return salesPlanOrderService.add(map);
    }

    @GetMapping("/stock_plan_order/list")
    @ApiOperation("查询")
    public Map select(@RequestParam Integer page_index, Integer page_size, String order_no
            , String salesGroup, String salesMan, String from, String to){
        Map map = stringToMap(page_index,page_size,order_no,salesGroup,salesMan,from,to);
        PageInfo<Map> list = salesPlanOrderService.list(map);
        Map result = new HashMap<String,Object>();
        result.put("data",list.getList());

        Map pagination = new HashMap<String,Object>();
        pagination.put("total",list.getTotal());
        pagination.put("page_index",list.getPageNum());
        pagination.put("page_size",list.getPageSize());

        result.put("code",0);
        result.put("msg","success");
        result.put("pagination",pagination);
        return result;
    }

    public Map stringToMap(Integer page_index, Integer page_size, String order_no, String salesGroup, String salesMan, String from, String to){
        Map map = new HashMap<String,Object>();
        map.put("page_index",page_index);
        map.put("page_size",page_size);
        map.put("order_no",order_no);
        map.put("salesGroup",salesGroup);
        map.put("salesMan",salesMan);
        map.put("from",from);
        map.put("to",to);
        return map;
    }

}
