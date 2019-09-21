package com.echo.crm.controller;

import com.echo.crm.entry.Product;
import com.echo.crm.service.ProductService;
import com.echo.crm.utils.Page;
import com.echo.crm.utils.PageUtils;
import com.echo.crm.utils.ResultInfo;
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
 * @create 2019-09-18 14:56
 */

@Slf4j
@RestController
public class ProductController implements BaseController<Product> {
    @Autowired
    private ProductService productService;

    @Override
    @PostMapping("/product")
    public ResultInfo<Product> add(@Valid @RequestBody Product product) {
        return ResultInfo.createResult(productService.add(product));
    }

    @Override
    @GetMapping("/product/{id:\\d+}")
    public ResultInfo<Product> findById(@PathVariable("id") Long id) {
        return ResultInfo.createResult(productService.findById(id));
    }

    @Override
    @PutMapping("/product")
    public ResultInfo<Product> update(@RequestBody Product product) {
        return ResultInfo.createResult(productService.update(product));
    }

    @Override
    public ResultInfo<Product> delete(Long id) {
        return null;
    }


    @Override
    @GetMapping("/products")
    public ResultInfo<Page<Product>> findByKeyword(@RequestParam(value = "page", required = false) Integer page,
                                                   @RequestParam(value = "limit", required = false) Integer limit,
                                                   @RequestParam(value = "q", required = false) String key) {
        PageList<Product> products = productService.findByKeyword(key, PageUtils.createPageBounds(page, limit));
        return ResultInfo.createResult(PageUtils.createPage(products));
    }
}
