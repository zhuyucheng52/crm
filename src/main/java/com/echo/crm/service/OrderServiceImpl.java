package com.echo.crm.service;

import com.echo.crm.SystemProperties;
import com.echo.crm.entry.Order;
import com.echo.crm.entry.Product;
import com.echo.crm.entry.User;
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
    public Order add(Order order) {
        Assert.isTrue(order.getProductNum() > 0, "商品数量必须大于0");
        Assert.isTrue(order.getPayment() >= 0, "订单金额必须大于等于0");

        Product p = productMapper.selectByPrimaryKey(order.getProductId());
        Assert.notNull(p, "商品不存在");

        if (properties.isOrderNeedApprove()) {
            Long approveId = order.getApproveId();
            User u = userMapper.selectByPrimaryKey(approveId);
            Assert.notNull(u, String.format("审批人[%s]不存在", approveId));
        }

        if (order.getCustomerId() != null) {
            // TODO yucheng 校验客户是否存在
        }

        orderMapper.insertSelective(order);
        return findById(order.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order update(Order order) {
        Long id = order.getId();
        Assert.notNull(id, "订单ID不能为空");

        Order o = orderMapper.selectByPrimaryKey(id);
        Assert.notNull(o, String.format("被修改订单[%s]不存在", id));
        Assert.isTrue(o.getApproveStatus() != Order.APPROVE_STATUS_PASS, "审批已经通过，禁止修改");
        orderMapper.updateByPrimaryKeySelective(order);

        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public Order delete(Long id) {
        // TODO yucheng
        return null;
    }

}
