package com.echo.crm.service;

import com.echo.crm.entry.Finance;
import com.echo.crm.mapper.FinanceMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author yucheng
 * @description
 * @create 2019-09-21 22:22
 */

@Service
public class FinanceServiceImpl implements FinanceService {
    @Autowired
    private FinanceMapper financeMapper;

    @Override
    public Finance findById(Long id) {
        Assert.notNull(id, "财务ID不能为空");
        return financeMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Finance add(Finance finance) {
        Integer status = finance.getApproveStatus();
        Assert.notNull(status, "财务状态不能为空");

        Integer type = finance.getType();
        Assert.notNull(type, "财务类型不能为空");

        financeMapper.insertSelective(finance);
        return financeMapper.selectByPrimaryKey(finance.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Finance update(Finance finance) {
        Long id = finance.getId();
        Finance f = findById(id);
        Assert.notNull(f, String.format("财务[%s]不存在", id));
        financeMapper.updateByPrimaryKeySelective(finance);
        return financeMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Finance delete(Long id) {
        Finance f = findById(id);
        Assert.notNull(f, String.format("财务记录[]不存在", id));
        f.setDisabled(1);
        financeMapper.updateByPrimaryKeySelective(f);
        return financeMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageList<Finance> findByKeyword(String key, PageBounds pageBounds) {
        return financeMapper.selectByKeyword(key, pageBounds);
    }
}
