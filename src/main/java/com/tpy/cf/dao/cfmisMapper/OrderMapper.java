package com.tpy.cf.dao.cfmisMapper;

import com.tpy.cf.entity.FormDetail;
import com.tpy.cf.entity.FormHeader;
import com.tpy.cf.entity.FormItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderMapper {

    public List<FormHeader> getFromByOrderNo(String order_no);

    public int addFromHead(FormHeader fromHeader);

    public int addFromDetails(List<FormDetail> fromDetails);

    public List<FormItem> getFormItemByType(String type);

    public String getFormName();

    public List<Map<String,Object>> getDetailById(Integer id);

}
