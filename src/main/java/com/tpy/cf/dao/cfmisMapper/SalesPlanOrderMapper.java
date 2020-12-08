package com.tpy.cf.dao.cfmisMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SalesPlanOrderMapper {

    public List<Map> list(Map map);

    public String getFlag(Integer id);

    public Map add(Map map);

    public Map modify(Map map);

    public int delete(Map map);

    public Map get(Integer id);
}
