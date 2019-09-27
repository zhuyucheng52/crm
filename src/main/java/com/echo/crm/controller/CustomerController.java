package com.echo.crm.controller;

import com.echo.crm.entry.Customer;
import com.echo.crm.service.CustomerService;
import com.echo.crm.utils.Page;
import com.echo.crm.utils.PageUtils;
import com.echo.crm.utils.ResultInfo;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yucheng
 * @description
 * @create 2019-09-27 10:43
 */

@RestController
public class CustomerController implements BaseController<Customer> {
    @Autowired
    private CustomerService customerService;

    @Override
    @GetMapping("/customer/{id:\\d+}")
    public ResultInfo<Customer> findById(@PathVariable("id") Long id) {
        return ResultInfo.createResult(customerService.findById(id));
    }

    @Override
    @GetMapping("/customers")
    public ResultInfo<Page<Customer>> findByKeyword(@RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "limit", required = false) Integer limit,
                                                    @RequestParam(value = "q", required = false) String key) {
        PageList<Customer> customers = customerService.findByKeyword(key, PageUtils.createPageBounds(page, limit));
        return ResultInfo.createResult(PageUtils.createPage(customers));
    }

    @Override
    @PostMapping("/customer")
    public ResultInfo<Customer> add(@RequestBody Customer customer) {
        return ResultInfo.createResult(customerService.add(customer));
    }

    @Override
    @PutMapping("/customer")
    public ResultInfo<Customer> update(@RequestBody Customer customer) {
        return ResultInfo.createResult(customerService.update(customer));
    }

    @Override
    @DeleteMapping("/customer/{id:\\d+}")
    public ResultInfo<Customer> delete(@PathVariable("id") Long id) {
        return ResultInfo.createResult(customerService.delete(id));
    }
}
