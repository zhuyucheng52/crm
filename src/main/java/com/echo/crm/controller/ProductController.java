package com.echo.crm.controller;

import com.echo.crm.dto.ResultDTO;
import com.echo.crm.entry.Product;
import com.echo.crm.service.ProductService;
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

import java.util.List;

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
    public ResultDTO<Product> addProduct(@RequestBody Product product) {
        return ResultDTO.createResult(productService.add(product));
    }

    @GetMapping("/product/{id:\\d+}")
    public ResultDTO<Product> getProduct(@PathVariable("id") Long id) {
        return ResultDTO.createResult(productService.getById(id));
    }

    @PutMapping("/product")
    public ResultDTO<Product> modifyProduct(@RequestBody Product product) {
        return ResultDTO.createResult(productService.update(product));
    }


    @GetMapping("/products")
    public ResultDTO<Page<Product>> getProducts(@RequestParam(value = "page", required = false) Integer page,
                                                @RequestParam(value = "limit", required = false) Integer limit,
                                                @RequestParam(value = "key", required = false) String key) {
        PageList<Product> products = productService.getProducts(key, PageUtils.createPageBounds(page, limit));
        return ResultDTO.createResult(PageUtils.createPage(products));
    }
}
