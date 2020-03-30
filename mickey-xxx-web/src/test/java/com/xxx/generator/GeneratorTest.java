package com.xxx.generator;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Sets;
import com.mickey.generator.CodeGenerator;
import com.mickey.generator.entity.MickeyConfig;
import com.mickey.generator.task.XmlTask;
import com.xxx.web.utils.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author J·K
 * @Description: TODO
 * @date 2020/3/30 4:01 下午
 */
@Slf4j
public class GeneratorTest extends BaseTest {
    @Resource
    DruidDataSource dataSource;
    /**
     * 生成所有表
     */
    @Test
    public void startTest(){
        new CodeGenerator(dataSource)
                .start();
    }

    /**
     * 生成po和xml文件
     */
    @Test
    public void xmlTest(){
        MickeyConfig config = new MickeyConfig();
        config.setTableNames(
                Sets.newHashSet("xxx")
        );
        new CodeGenerator(dataSource,config)
                .registerTask(XmlTask.class)
                .start();
    }

    /**
     * 生成指定的表
     */
    @Test
    public void oneTalbeTest(){
        MickeyConfig config = new MickeyConfig();
        config.setTableNames(
                Sets.newHashSet("xxx")
        )
                .setBasePackage("com.xxx");
        new CodeGenerator(dataSource,config)
                .start();
    }

}
