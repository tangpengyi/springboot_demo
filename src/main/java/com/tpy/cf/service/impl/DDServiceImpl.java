package com.tpy.cf.service.impl;

import com.tpy.cf.dao.cfmisMapper.DDMapper;
import com.tpy.cf.service.DDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DDServiceImpl implements DDService {

    @Autowired
    private DDMapper ddMapper;

    @Override
    public Map info(String start_time) {
        Map map = new HashMap<String,Object>();
        List<Map> summary = ddMapper.summary(start_time);
        map.put("summary",summary);

        List<Map> detail = ddMapper.detail(start_time);
        List<Map> collect = detail.stream()
                .map(m->{
                    m.put("stay_time",minutesToTime(m.get("stay_time")));
                    return m;
                })
                .sorted((a,b)->{
                    return Integer.parseInt(b.get("id").toString()) - Integer.parseInt(a.get("id").toString());
                })
                .collect(Collectors.toList());
        map.put("detail",collect);

        return map;
    }

    @Override
    public Map summay(String start_time) {
        List<Map> summary = ddMapper.summary(start_time);
        List<Map> day_collect = summary.stream()
                .filter(m -> {
                    return m.get("range").equals("D");
                })
                .map(m->{
                    m.put("proportion",m.get("proportion").toString()+"%");
                    return m;
                })
                .collect(Collectors.toList());

        List<Map> month_collect = summary.stream()
                .filter(m -> {
                    return m.get("range").equals("M");
                })
                .map(m->{
                    m.put("proportion",m.get("proportion").toString()+"%");
                    return m;
                })
                .collect(Collectors.toList());

        Map map = new HashMap<String,Object>();
        map.put("D",day_collect);
        map.put("M",month_collect);
        return map;
    }

    @Override
    public List<Map> details(String start_time) {

        List<Map> list = ddMapper.detail(start_time);
        List<Map> collect = list.stream()
                .map(m->{
                    m.put("stay_time",minutesToTime(m.get("stay_time")));
                    return m;
                })
                .sorted((a,b)->{
                    return Integer.parseInt(b.get("id").toString()) - Integer.parseInt(a.get("id").toString());
                })
                .collect(Collectors.toList());

        return collect;
    }


    public String minutesToTime(Object minutes){
        if(minutes == null){
            return null;
        }
        Integer min = (Integer) minutes;
        int minu = min%60;
        int hours = min/60%24;
        int day = min/60/24;
        return day+"天 "+hours+"时 "+minu+"分";
    }
}
