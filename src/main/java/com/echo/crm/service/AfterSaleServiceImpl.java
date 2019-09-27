package com.echo.crm.service;

import com.echo.crm.entry.AfterSale;
import com.echo.crm.mapper.AfterSaleMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Service
public class AfterSaleServiceImpl implements AfterSaleService {
    @Autowired
    private AfterSaleMapper aftersaleMapper;

    @Override
    public AfterSale findById(Long id) {
        Assert.notNull(id, "售后ID不能为空");
        return aftersaleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageList<AfterSale> findByKeyword(String key, PageBounds pageBounds) {
        return aftersaleMapper.selectByKeyword(key, pageBounds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AfterSale add(AfterSale aftersale) {
        aftersaleMapper.insertSelective(aftersale);
        return findById(aftersale.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AfterSale update(AfterSale aftersale) {
        Long id = aftersale.getId();
        AfterSale as = findById(id);
        Assert.notNull(as, String.format("售后记录[%s]不存在", id));
        aftersaleMapper.updateByPrimaryKeySelective(aftersale);

        return aftersaleMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AfterSale delete(Long id) {
        AfterSale as = findById(id);
        Assert.notNull(as, String.format("售后记录[%s]不存在", id));
        as.setDisabled(1);
        aftersaleMapper.updateByPrimaryKeySelective(as);
        return aftersaleMapper.selectByPrimaryKey(id);
    }
}