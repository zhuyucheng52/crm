package com.echo.crm.controller;

import com.echo.crm.entry.Product;
import com.echo.crm.service.ProductService;
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
 * @create 2019-09-18 14:56
 */

@Slf4j
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResultInfo<Object> add(@Valid @RequestBody Product product) {
        productService.add(product);
        return ResultInfo.createEmptyResult();
    }

    @GetMapping("/product/{id:\\d+}")
    public ResultInfo<Product> findById(@PathVariable("id") Long id) {
        return ResultInfo.createResult(productService.findById(id));
    }

    @PutMapping("/product")
    public ResultInfo<Object> update(@RequestBody Product product) {
    	productService.update(product);
        return ResultInfo.createEmptyResult();
    }

    @DeleteMapping("/product/{id:\\d+}")
    public ResultInfo<Object> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResultInfo.createEmptyResult();
    }

    @GetMapping("/product/categoryid/{id:\\d+}")
    private ResultInfo<PageList<Product>> findProductByCategory(@PathVariable("id") Long categoryId) {
        PageList<Product> products = productService.findByCategoryId(categoryId);
        return ResultInfo.createResult(products);
    }

    @GetMapping("/products")
    public ResultInfo<Page<Product>> findByKeyword(@RequestParam(value = "page", required = false) Integer page,
                                                   @RequestParam(value = "limit", required = false) Integer limit,
                                                   @RequestParam(value = "q", required = false) String key) {
        PageList<Product> products = productService.findByKeyword(key, PageUtils.createPageBounds(page, limit));
        return ResultInfo.createResult(PageUtils.createPage(products));
    }

}
