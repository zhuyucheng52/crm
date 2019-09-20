package com.echo.crm.controller;

import com.echo.crm.dto.PasswordModifyDTO;
import com.echo.crm.utils.ResultInfo;
import com.echo.crm.entry.User;
import com.echo.crm.service.UserService;
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

import javax.validation.Valid;


/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id:\\d+}")
    public ResultInfo<User> getUser(@PathVariable("id") Long id) {
        return ResultInfo.createResult(userService.getUserById(id));
    }

    @GetMapping("/users")
    public ResultInfo<Page<User>> getUser(@RequestParam(value = "page", required = false) Integer page,
                                          @RequestParam(value = "limit", required = false) Integer limit,
                                          @RequestParam(value = "q", required = false) String key) {
        PageList<User> users = userService.getUsers(key, PageUtils.createPageBounds(page, limit));
        return ResultInfo.createResult(PageUtils.createPage(users));
    }

    @PostMapping("/user")
    public ResultInfo<User> addUser(@Valid @RequestBody User user) {
        return ResultInfo.createResult(userService.add(user));
    }

    @PutMapping("/user")
    public ResultInfo<User> modifyUser(@RequestBody User user) {
        return ResultInfo.createResult(userService.update(user));
    }

    @PutMapping("/user/password/{id:\\d+}")
    public ResultInfo<Object> modifyPassword(@PathVariable("id") Long id,
                                             @RequestBody PasswordModifyDTO password) {
        userService.updatePassword(id, password.getOldPassword(), password.getNewPassword());
        return ResultInfo.createEmptyResult();
    }

}
