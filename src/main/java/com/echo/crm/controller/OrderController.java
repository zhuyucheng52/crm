package com.echo.crm.controller;

import com.echo.crm.utils.ResultInfo;
import com.echo.crm.entry.Order;
import com.echo.crm.service.OrderService;
import com.echo.crm.utils.Page;
import com.echo.crm.utils.PageUtils;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

@Slf4j
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/{id:\\d+}")
    public ResultInfo<Order> getOrder(@PathVariable("id") Long id) {
        return ResultInfo.createResult(orderService.getOrderById(id));
    }

    @GetMapping("/orders")
    public ResultInfo<Page<Order>> getOrder(@RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "limit", required = false) Integer limit,
                                            @RequestParam(value = "q", required = false) String key) {
        PageList<Order> orders = orderService.getOrders(key, PageUtils.createPageBounds(page, limit));
        return ResultInfo.createResult(PageUtils.createPage(orders));
    }

    @PostMapping("/order")
    public ResultInfo<Order> addOrder(@Valid @RequestBody Order order) {
        return ResultInfo.createResult(orderService.add(order));
    }

    @PutMapping("/order")
    public ResultInfo<Order> modifyOrder(@RequestBody Order order) {
        return ResultInfo.createResult(orderService.update(order));
    }

}
