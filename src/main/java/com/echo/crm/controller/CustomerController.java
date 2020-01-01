package com.echo.crm.controller;

import com.echo.crm.entry.Customer;
import com.echo.crm.service.CustomerService;
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
public class CustomerController {
    @Autowired
	private CustomerService customerService;

    @GetMapping("/customer/{id:\\d+}")
    public ResultInfo<Customer> findById(@PathVariable("id") Long id) {
        return ResultInfo.createResult(customerService.findById(id));
    }

    @GetMapping("/customers")
    public ResultInfo<Page<Customer>> findByKeyword(@RequestParam(value = "page", required = false) Integer page,
                                                     @RequestParam(value = "limit", required = false) Integer limit,
                                                     @RequestParam(value = "q", required = false) String key) {
        PageList<Customer> customers = customerService.findByKeyword(key, PageUtils.createPageBounds(page, limit));
        return ResultInfo.createResult(PageUtils.createPage(customers));
    }

    @PostMapping("/customer")
    public ResultInfo<Object> add(@Valid @RequestBody Customer customer) {
    	customerService.add(customer);
    	return ResultInfo.createEmptyResult();
    }

    @PutMapping("/customer")
    public ResultInfo<Object> update(@RequestBody Customer customer) {
        customerService.update(customer);
        return ResultInfo.createEmptyResult();
    }

    @DeleteMapping("/customer/{id:\\d+}")
    public ResultInfo<Object> delete(@PathVariable("id") Long id) {
        customerService.delete(id);
        return ResultInfo.createEmptyResult();
    }
}
