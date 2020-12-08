package com.tpy.cf.dao.cfmisMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SalesPlanOrderDetailsMapper {
    public int add(List<Map> list);

    public int addByOne(Map map);

    public int delete(Map map);

    public int update(Map map);

    public List<Map> selectByOrderId(Integer id);
}
