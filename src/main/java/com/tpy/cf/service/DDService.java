package com.tpy.cf.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface DDService {
    public Map info(String start_time);

    public Map summay(String start_time);

    public List<Map> details(String start_time);
}
