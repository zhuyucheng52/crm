package com.echo.crm.controller;

import com.echo.crm.entry.ProductCategory;
import com.echo.crm.service.ProductCategoryService;
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
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping("/product/category")
    public ResultInfo<Object> add(@Valid @RequestBody ProductCategory category) {
        productCategoryService.add(category);
        return ResultInfo.createEmptyResult();
    }

    @GetMapping("/product/category/{id:\\d+}")
    public ResultInfo<ProductCategory> findById(@PathVariable("id") Long id) {
        return ResultInfo.createResult(productCategoryService.findById(id));
    }

    @PutMapping("/product/category")
    public ResultInfo<Object> update(@RequestBody ProductCategory category) {
    	productCategoryService.update(category);
    	return ResultInfo.createEmptyResult();
    }

    @DeleteMapping("/product/category/{id:\\d+}")
    public ResultInfo<Object> delete(@PathVariable("id") Long id) {
        productCategoryService.delete(id);
        return ResultInfo.createEmptyResult();
    }


    @GetMapping("/product/categories")
    public ResultInfo<Page<ProductCategory>> findByKeyword(@RequestParam(value = "page", required = false) Integer page,
                                                   @RequestParam(value = "limit", required = false) Integer limit,
                                                   @RequestParam(value = "q", required = false) String key) {
        PageList<ProductCategory> products = productCategoryService.findByKeyword(key, PageUtils.createPageBounds(page, limit));
        return ResultInfo.createResult(PageUtils.createPage(products));
    }

}
