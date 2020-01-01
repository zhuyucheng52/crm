package com.echo.crm.mapper;

import com.echo.crm.entry.Customer;
import com.echo.crm.utils.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Repository
public interface CustomerMapper extends BaseMapper<Customer> {
	Customer selectById(@Param("id") Long id);
}
