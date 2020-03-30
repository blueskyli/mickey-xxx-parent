package com.xxx.dao;

import com.mickey.mybatis.dao.impl.BaseDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author J·K
 * @Description:  MickeyDao
 * @date 2018/7/27 16:43
 */
@Repository("baseDao")
public class MickeyDao extends BaseDao
{
    @Override
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory SqlSessionFactory)
    {
        super.setSqlSessionFactory(SqlSessionFactory);
    }
}
