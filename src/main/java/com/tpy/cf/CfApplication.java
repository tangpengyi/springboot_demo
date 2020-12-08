package com.tpy.cf;

import jdk.nashorn.internal.objects.annotations.ScriptClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CfApplication {

    public static void main(String[] args) {
        SpringApplication.run(CfApplication.class, args);
    }

}
