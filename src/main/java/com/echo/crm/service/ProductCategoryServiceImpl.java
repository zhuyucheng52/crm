package com.echo.crm.service;

import com.echo.crm.entry.ProductCategory;
import com.echo.crm.mapper.ProductCategoryMapper;
import com.echo.crm.mapper.ProductMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductCategory findById(Long id) {
        return productCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ProductCategory category) {
        List<ProductCategory> categories = productCategoryMapper.selectByName(category.getName());
        Assert.isTrue(CollectionUtils.isEmpty(categories), "商品类目名称已存在");
        productCategoryMapper.insert(category);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(ProductCategory t) {
        List<ProductCategory> categories = productCategoryMapper.selectSameNameCategories(t.getName(), t.getId());
        Assert.isTrue(CollectionUtils.isEmpty(categories), "商品类目名称已存在");
        return productCategoryMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        int productCount = productMapper.selectCountByCategoryId(id);
        Assert.isTrue(productCount == 0, "该类目下有商品不允许删除!");
        productCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageList<ProductCategory> findByKeyword(String key, PageBounds pageBounds) {
        return productCategoryMapper.selectByKeyword(key, pageBounds);
    }
}
