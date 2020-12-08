package com.tpy.cf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tpy.cf.api.Result;
import com.tpy.cf.dao.cfmisMapper.SalesPlanOrderDetailsMapper;
import com.tpy.cf.dao.cfmisMapper.SalesPlanOrderMapper;
import com.tpy.cf.service.SalesPlanOrderService;
import com.tpy.cf.utils.CommonsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SalesPlanOrderServiceImpl implements SalesPlanOrderService {

    @Autowired
    private SalesPlanOrderMapper salesPlanOrderMapper;

    @Autowired
    private SalesPlanOrderDetailsMapper salesPlanOrderDetailsMapper;

    @Override
    @Transactional(value="cfmisTransactionManager")
    public Result add(Map map) {

        Map header = salesPlanOrderMapper.add(map);

        List<Map> details = (List<Map>) map.get("details");

        if(details != null){
            for (Map detail : details){
                detail.put("modify_user","冯晓杰");
                detail.put("order_id",header.get("id"));
            }
            salesPlanOrderDetailsMapper.add(details);
        }

        return header == null ? Result.failed("新增失败"):Result.success(header);
    }

    @Override
    @Transactional(value="cfmisTransactionManager")
    public Result modify(Map map) {

        String flag = salesPlanOrderMapper.getFlag((Integer) map.get("id"));
        if(!flag.equals(map.get("flag"))){
            return Result.failed("数据已被修改，请重新刷新数据在做修改");
        }

        Map add = salesPlanOrderMapper.modify(map);

        List<Map> details = (List<Map>) map.get("details");

        StringBuffer stringBuffer = new StringBuffer();

        for (Map detail : details){
            detail.put("modify_user","冯晓杰");
            if(detail != details.get(0)){
                stringBuffer.append(",");
            }
            stringBuffer.append(detail.get("id"));

            int update = salesPlanOrderDetailsMapper.update(detail);
            if(update == 0){
                detail.put("modify_user","唐鹏翼");
                detail.put("order_id",map.get("id"));
                salesPlanOrderDetailsMapper.addByOne(detail);
            }
        }

        // 删除未传入的明细
        Map detail_delate = new HashMap<String,String>();
        detail_delate.put("list_id",stringBuffer.toString());
        detail_delate.put("order_id",map.get("id"));
        salesPlanOrderDetailsMapper.delete(detail_delate);

        return add == null ? Result.failed("修改失败"):Result.success(add);
    }

    @Override
    public PageInfo<Map> list(Map map) {

        PageHelper.startPage((int) map.get("page_index"),(int) map.get("page_size"));
        List<Map> order_list = salesPlanOrderMapper.list(map);
        PageInfo<Map> pageInfo = new PageInfo<>(headerDataToStr(order_list));

        return pageInfo;
    }

    @Override
    public Result delete(int id) {
        Map map = new HashMap<String,Object>();
        map.put("id",id);
        map.put("modify_user","唐鹏翼");
        int delete = salesPlanOrderMapper.delete(map);

        return delete == 0 ? Result.failed("删除失败"):Result.success(null);
    }

    @Override
    public Map get(Integer id) {
        Map map = salesPlanOrderMapper.get(id);
        if(map == null)
            return null;

        List<Map> list = salesPlanOrderDetailsMapper.selectByOrderId(id);
        Map header = headerDataToStr(map);
        header.put("details",detailDataToStr(list));

        return header;
    }

    public List<Map> detailDataToStr(List<Map> maps){
        if(maps == null)
            return maps;

        for(Map map : maps){
            map.put("create_time", map.get("create_time").toString());
            Object expect_date = map.get("expect_date");
            if(expect_date == null)
                map.put("expect_date","");
            else
                map.put("expect_date", expect_date.toString());


            map.put("modify_time", map.get("modify_time").toString());
            Object confirm_date = map.get("confirm_date");
            if(confirm_date == null)
                map.put("confirm_date","");
            else
                map.put("confirm_date", confirm_date.toString());
        }
        return maps;
    }

    public List<Map> headerDataToStr(List<Map> maps){
        if(maps == null)
            return maps;

        for(Map map : maps){
            Object order_date = map.get("order_date");
            if("1900-01-01 00:00:00.0".equals(order_date.toString()))
                map.put("order_date","");
            else
                map.put("order_date", order_date.toString());

            Object accept_date = map.get("accept_date");
            if("1900-01-01 00:00:00.0".equals(accept_date.toString()))
                map.put("accept_date","");
            else
                map.put("accept_date", accept_date.toString());


            Object approve_time = map.get("approve_time");
            if("1900-01-01 00:00:00.0".equals(approve_time.toString()))
                map.put("approve_time","");
            else
                map.put("approve_time", approve_time.toString());

            map.put("order_time", map.get("order_time").toString());
            map.put("create_time", map.get("create_time").toString());
            map.put("modify_time", map.get("modify_time").toString());
        }
        return maps;
    }

    public Map headerDataToStr(Map map){
        if(map == null){
            return map;
        }
        map.put("create_time", map.get("create_time").toString());
        Object approve_time = map.get("approve_time");
        if(approve_time == null)
            map.put("approve_time","");
        else
            map.put("approve_time", approve_time.toString());
        map.put("modify_time", map.get("modify_time").toString());
        Object accept_date = map.get("accept_date");
        if(accept_date == null)
            map.put("accept_date","");
        else
            map.put("accept_date", accept_date.toString());
        map.put("order_date", map.get("order_date").toString());
        return map;
    }
}
