package com.xxx.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.xxx"})
public class WebStartUp
{
    public static void main(String[] args)
    {
        SpringApplication.run(WebStartUp.class, args);
    }
}
