package com.echo.crm.service;

import com.echo.crm.entry.Customer;
import com.echo.crm.mapper.CustomerMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author yucheng
 * @description
 * @create 2019-09-27 10:42
 */

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer findById(Long id) {
        Assert.notNull(id, "客户ID不能为空");
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer add(Customer customer) {
        customerMapper.insertSelective(customer);
        return customerMapper.selectByPrimaryKey(customer.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer update(Customer customer) {
        Long id = customer.getId();
        Customer u = findById(id);
        Assert.notNull(u, String.format("客户[%s]不存在", id));

        customerMapper.updateByPrimaryKeySelective(customer);
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Customer customer = findById(id);
        Assert.notNull(customer, String.format("客户[%s]不存在", id));

        customer.setDisabled(1);
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public PageList<Customer> findByKeyword(String key, PageBounds pageBounds) {
        return customerMapper.selectByKeyword(key, pageBounds);
    }
}
