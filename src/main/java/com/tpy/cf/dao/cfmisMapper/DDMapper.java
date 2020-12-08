package com.tpy.cf.dao.cfmisMapper;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DDMapper {
    public List<Map> summary(String start_time);

    public List<Map> detail(String current_month_start);
}
