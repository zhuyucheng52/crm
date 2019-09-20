package com.echo.crm.service;

import com.echo.crm.entry.Product;
import com.echo.crm.mapper.ProductMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-09-18 16:07
 */

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;


    @Override
    public Product getById(Long id) {
        Assert.notNull(id, "ID不能为空");
        return productMapper.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Product add(Product product) {
        List<Product> p = productMapper.findByName(product.getName());
        Assert.isTrue(p.isEmpty(), "产品名已存在");
        productMapper.add(product);

        return getById(product.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Product update(Product product) {
        Long id = product.getId();
        Assert.notNull(id, "商品ID不能为空");
        Product p = getById(id);
        if (product.getDisabled() != null) {
            p.setDisabled(product.getDisabled());
        }
        if (product.getName() != null) {
            Assert.isTrue(productMapper.findOtherByName(id, product.getName()).isEmpty(), "产品名已存在");
            p.setName(product.getName());
        }
        if (product.getRemark() != null) {
            p.setRemark(product.getRemark());
        }

        productMapper.update(p);
        return getById(id);
    }

    @Override
    public PageList<Product> getProducts(String key, PageBounds pageBounds) {
        return productMapper.findProducts(key, pageBounds);
    }
}
