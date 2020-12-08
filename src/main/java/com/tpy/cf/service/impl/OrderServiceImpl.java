package com.tpy.cf.service.impl;

import com.tpy.cf.dao.cfmisMapper.OrderMapper;
import com.tpy.cf.entity.FormDetail;
import com.tpy.cf.entity.FormHeader;
import com.tpy.cf.entity.FormItem;
import com.tpy.cf.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

//    @Override
//    public List getHeaderByOrderNo(String orderNo) throws Exception {
//        List<FormHeader> list = orderMapper.getFromByOrderNo(orderNo);
//        return list;
//    }
//
//    @Override
//    @Transactional(transactionManager="cfmisTransactionManager")
//    public ResponseResult addHeader(FormHeader fromHeader) throws Exception {
//
//        // 新增主表
//        int i = orderMapper.addFromHead(fromHeader);
//        if(i <= 0)
//            return Commons.getFialResult();
//        //添加明细
//        List<FormDetail> fromDetails = getFromDetailsByFromHeader(fromHeader);
//        try{
//            orderMapper.addFromDetails(fromDetails);
//        }
//        catch(Exception e){
//            log.error(e.getMessage());
//            // 可以定义异常类，将打印日志放在该类中执行
//            throw new Exception();
//        }
//
//        return Commons.getSuccessResult(null);
//    }
//
//    @Override
//    public Map<String,Object> getItemByType(String type) {
//        List<FormItem> item = orderMapper.getFormItemByType(type);
//
//        // 从数据库中生成一个表单名称，返回给用户
//
//        String formName = orderMapper.getFormName();
//        Map<String,Object> map = new HashMap<>();
//        map.put("code",0);
//        map.put("msg","操作成功");
//        map.put("date",item);
//        map.put("order_no",formName);
//        return map;
//    }
//
//    @Override
//    public ResponseResult getDetailById(Integer id) {
//        List<Map<String, Object>> detailById = orderMapper.getDetailById(id);
//        if(detailById.size() == 0){
//            return Commons.getSuccessResult(null);
//        }
//        return Commons.getSuccessResult(detailById.get(0));
//    }
//
//    private List<FormDetail> getFromDetailsByFromHeader(FormHeader formHeader){
//        List<FormDetail> fromdetails = formHeader.getFormDetial();
//        for(FormDetail fromDetail : fromdetails){
//            fromDetail.setHeader_id(fromDetail.getId());
//        }
//        return fromdetails;
//    }

}
