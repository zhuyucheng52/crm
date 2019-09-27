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
    public Product findById(Long id) {
        Assert.notNull(id, "ID不能为空");
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Product add(Product product) {
        List<Product> p = productMapper.findByName(product.getName());
        Assert.isTrue(p.isEmpty(), "产品名已存在");
        productMapper.insertSelective(product);

        return productMapper.selectByPrimaryKey(product.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Product update(Product product) {
        Long id = product.getId();
        Product p = findById(id);
        if (product.getName() != null) {
            Assert.isTrue(productMapper.findOtherByName(id, product.getName()).isEmpty(), "产品名已存在");
        }

        productMapper.updateByPrimaryKeySelective(product);
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public Product delete(Long id) {
        // TODO yucheng
        return null;
    }

    @Override
    public PageList<Product> findByKeyword(String key, PageBounds pageBounds) {
        return productMapper.selectByKeyword(key, pageBounds);
    }
}