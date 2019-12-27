package com.echo.crm.service;

import com.echo.crm.entry.Role;
import com.echo.crm.exception.NotSupportException;
import com.echo.crm.mapper.RoleMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:43
 */

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role findById(Long id) {
        Assert.notNull(id, "角色ID不能为空");
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageList<Role> findByKeyword(String key, PageBounds pageBounds) {
        return roleMapper.selectByKeyword(key, pageBounds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Role role) {
    	roleMapper.insert(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Role role) {
    	return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        roleMapper.deleteByPrimaryKey(id);
    }
}
