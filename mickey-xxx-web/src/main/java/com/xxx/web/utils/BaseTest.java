package com.xxx.web.utils;

import com.xxx.web.WebStartUp;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebStartUp.class)
public class BaseTest
{
    @Before
    public void before()
    {
        System.out.println("测试开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
    
    @After
    public void after()
    {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<测试完成");
    }
}
