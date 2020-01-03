package com.echo.crm.controller;

import com.echo.crm.entry.Order;
import com.echo.crm.service.OrderService;
import com.echo.crm.utils.Page;
import com.echo.crm.utils.PageUtils;
import com.echo.crm.utils.ResultInfo;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResultInfo<Order> findById(@PathVariable("id") Long id) {
        return ResultInfo.createResult(orderService.findById(id));
    }

    @GetMapping("/orders")
    public ResultInfo<Page<Order>> findByKeyword(@RequestParam(value = "page") Integer page,
                                                 @RequestParam(value = "limit") Integer limit,
                                                 @RequestParam(value = "q", required = false) String key) {
        PageList<Order> orders = orderService.findByKeyword(key, PageUtils.createPageBounds(page, limit));
        return ResultInfo.createResult(PageUtils.createPage(orders));
    }

    @PostMapping("/order")
    public ResultInfo<Object> add(@Valid @RequestBody Order order) {
        orderService.add(order);
        return ResultInfo.createEmptyResult();
    }

    @PutMapping("/order")
    public ResultInfo<Object> update(@RequestBody Order order) {
        orderService.update(order);
        return ResultInfo.createEmptyResult();
    }

    @DeleteMapping("/order/{id:\\d+}")
    public ResultInfo<Object> delete(@PathVariable("id") Long id) {
        orderService.delete(id);
        return ResultInfo.createEmptyResult();
    }

}
