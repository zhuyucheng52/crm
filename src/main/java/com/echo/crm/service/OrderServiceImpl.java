package com.echo.crm.service;

import com.echo.crm.SystemProperties;
import com.echo.crm.entry.Order;
import com.echo.crm.mapper.OrderMapper;
import com.echo.crm.mapper.ProductMapper;
import com.echo.crm.mapper.UserMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SystemProperties properties;

    @Override
    public Order findById(Long id) {
        Assert.notNull(id, "订单ID不能为空");
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageList<Order> findByKeyword(String key, PageBounds pageBounds) {
        return orderMapper.selectByKeyword(key, pageBounds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Order order) {
        Assert.isTrue(order.getProductNum() > 0, "商品数量必须大于0");
        Assert.isTrue(order.getPayment() >= 0, "订单金额必须大于等于0");
        orderMapper.insertSelective(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Order order) {
        Long id = order.getId();
        Assert.notNull(id, "订单ID不能为空");

        Order o = orderMapper.selectByPrimaryKey(id);
        Assert.notNull(o, String.format("被修改订单[%s]不存在", id));
        Assert.isTrue(o.getApproveStatus() != Order.APPROVE_STATUS_PASS, "审批已经通过，禁止修改");
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(Long id) {
    	return orderMapper.deleteByPrimaryKey(id);
    }

}
