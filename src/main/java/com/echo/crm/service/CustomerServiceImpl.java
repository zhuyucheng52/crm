package com.echo.crm.service;

import com.echo.crm.entry.Customer;
import com.echo.crm.mapper.CustomerMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public Customer findById(Long id) {
		Assert.notNull(id, "客户ID不能为空");
		return customerMapper.selectById(id);
	}

	@Override
	public PageList<Customer> findByKeyword(String key, PageBounds pageBounds) {
		return customerMapper.selectByKeyword(key, pageBounds);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void add(Customer customer) {
		customerMapper.insert(customer);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(Customer customer) {
		return customerMapper.updateByPrimaryKeySelective(customer);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		customerMapper.deleteByPrimaryKey(id);
	}
}
