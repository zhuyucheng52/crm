package com.echo.crm.service;

import com.echo.crm.entry.Product;
import com.echo.crm.mapper.ProductMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.commons.collections4.CollectionUtils;
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
        Assert.notNull(id, "产品ID不能为空");
        return productMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Product product) {
        List<Product> p = productMapper.selectByName(product.getName());
        Assert.isTrue(p.isEmpty(), "产品名已存在");
        product.setCategoryId(product.getCategory().getId());
        productMapper.insertSelective(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Product product) {
        List<Product> products = productMapper.selectSameNameProduct(product.getName(), product.getId());
        Assert.isTrue(CollectionUtils.isEmpty(products), "产品名称已存在");
        return productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
    	productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageList<Product> findByKeyword(String key, PageBounds pageBounds) {
        return productMapper.selectByKeyword(key, pageBounds);
    }
}
