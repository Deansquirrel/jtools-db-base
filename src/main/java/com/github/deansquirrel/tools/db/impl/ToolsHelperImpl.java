package com.github.deansquirrel.tools.db.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.deansquirrel.tools.db.Constant;
import com.github.deansquirrel.tools.db.DynamicRoutingDataSource;
import com.github.deansquirrel.tools.db.IToolsDbHelper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ToolsHelperImpl implements IToolsDbHelper {

    private final JdbcTemplate jdbcTemplate;
    private final DynamicRoutingDataSource dynamicRoutingDataSource;

    public ToolsHelperImpl(@Qualifier(value = Constant.BEAN_JDBC_TEMPLATE) JdbcTemplate jdbcTemplate,
                           @Qualifier(value = Constant.DYNAMIC_ROUTEING_DATASOURCE) DynamicRoutingDataSource dynamicRoutingDataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dynamicRoutingDataSource = dynamicRoutingDataSource;
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    @Override
    public boolean isExistDataSource(String key) {
        return this.dynamicRoutingDataSource.isExistDataSource(key);
    }

    @Override
    public Set<String> keySet() {
        return this.dynamicRoutingDataSource.keySet();
    }

    @Override
    public void addDataSource(String key, DruidDataSource dataSource) {
        this.dynamicRoutingDataSource.addDataSource(key, dataSource);
    }

    @Override
    public void removeDataSource(String key) {
        this.dynamicRoutingDataSource.removeDataSource(key);
    }

    @Override
    public void clear() {
        this.dynamicRoutingDataSource.clear();
    }

    @Override
    public long size() {
        return this.dynamicRoutingDataSource.size();
    }

    @Override
    public void setDataSourceKey(String key) {
        this.dynamicRoutingDataSource.setDataSourceKey(key);
    }

    @Override
    public void remove() {
        this.dynamicRoutingDataSource.remove();
    }

}
