package com.xxx.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.mickey.druid.config.AbstractDruidDBConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

/**
 * @author J·K
 * @Description: 数据源
 * @date 2018/7/27 15:04
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.xxx.mapper", sqlSessionFactoryRef = "SqlSessionFactory")
public class DBConfig extends AbstractDruidDBConfig
{
    @Value("${mickey.datasource.url}")
    private String url;

    @Value("${mickey.datasource.username}")
    private String username;

    @Value("${mickey.datasource.password}")
    private String password;

    // 注册dataSource
    @Bean(name = "datasource", initMethod = "init", destroyMethod = "close")
    @Primary
    public DruidDataSource dataSource()
    {
        DruidDataSource dataSource = super.createDataSource(url, username, password);
        List<Filter> filters = dataSource.getProxyFilters();
        WallFilter filter = (WallFilter) filters.get(2);
        WallConfig wallConfig = new WallConfig();
        wallConfig.setMultiStatementAllow(true);
        filter.setConfig(wallConfig);
        dataSource.setProxyFilters(filters);
        return dataSource;
    }

    @Bean(name = "SqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception
    {
        return super.sqlSessionFactory(dataSource());
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager()
    {
        return new DataSourceTransactionManager(dataSource());
    }
}
