package com.echo.crm.controller;

import com.echo.crm.entry.Permission;
import com.echo.crm.service.PermissionService;
import com.echo.crm.utils.Page;
import com.echo.crm.utils.PageUtils;
import com.echo.crm.utils.ResultInfo;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yucheng
 * @description
 * @create 2019-12-26 3:54 下午
 */

@Slf4j
@RestController
public class PermissionController {
	@Autowired
	private PermissionService permissionService;

	@GetMapping("/permissions")
	public ResultInfo<Page<Permission>> findByKeyword(@RequestParam(value = "page", required = false) Integer page,
													  @RequestParam(value = "limit", required = false) Integer limit,
													  @RequestParam(value = "q", required = false) String key) {
		PageList<Permission> permissions = permissionService.findByKeyword(key, PageUtils.createPageBounds(page, limit));
		return ResultInfo.createResult(PageUtils.createPage(permissions));
	}

	@PutMapping("/permission")
	public ResultInfo<Object> update(@RequestBody Permission permission) {
		permissionService.update(permission);
		return ResultInfo.createEmptyResult();
	}

	@DeleteMapping("/permission/{id:\\d+}")
	public ResultInfo<Object> delete(@PathVariable("id") Long id) {
		permissionService.delete(id);
		return ResultInfo.createEmptyResult();
	}
}
