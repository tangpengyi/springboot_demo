package com.tpy.cf.controller;

import com.tpy.cf.annototion.ResponseResult;
import com.tpy.cf.service.DDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dd")
public class DDController {

    @Autowired
    private DDService ddService;

    @GetMapping("/info")
//    @ResponseResult
    public Map info(){
        return ddService.info("2020-11-19");
    }

    @GetMapping("/summay")
    @ResponseResult
    public Map summay(){
        return ddService.summay("2020-11-19");
    }

    @GetMapping("/details")
    @ResponseResult
    public List<Map> details(){
        return ddService.details("2020-11-19");
    }

}
