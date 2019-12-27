package com.echo.crm.controller;

import com.echo.crm.entry.Role;
import com.echo.crm.exception.NotSupportException;
import com.echo.crm.service.RoleService;
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
 * @create 2019-09-17 09:42
 */

@Slf4j
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/role/{id:\\d+}")
    public ResultInfo<Role> findById(@PathVariable("id") Long id) {
        return ResultInfo.createResult(roleService.findById(id));
    }

    @GetMapping("/roles")
    public ResultInfo<Page<Role>> findByKeyword(@RequestParam(value = "page", required = false) Integer page,
                                                     @RequestParam(value = "limit", required = false) Integer limit,
                                                     @RequestParam(value = "q", required = false) String key) {
        PageList<Role> roles = roleService.findByKeyword(key, PageUtils.createPageBounds(page, limit));
        return ResultInfo.createResult(PageUtils.createPage(roles));
    }

    @PostMapping("/role")
    public ResultInfo<Object> add(@Valid @RequestBody Role role) {
    	roleService.add(role);
    	return ResultInfo.createEmptyResult();
    }

    @PutMapping("/role")
    public ResultInfo<Object> update(@RequestBody Role role) {
        roleService.update(role);
        return ResultInfo.createEmptyResult();
    }

    @DeleteMapping("/role/{id:\\d+}")
    public ResultInfo<Object> delete(@PathVariable("id") Long id) {
        roleService.delete(id);
        return ResultInfo.createEmptyResult();
    }
}
