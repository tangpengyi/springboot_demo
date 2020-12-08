package com.tpy.cf.utils;

import cn.hutool.core.convert.Convert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class HutoolTest {

    @Test
    public void hutool(){
        System.out.println(Convert.toStr(1));
    }

}
