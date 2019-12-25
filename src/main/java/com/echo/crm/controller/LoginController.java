package com.echo.crm.controller;

import com.echo.crm.dto.TokenHandler;
import com.echo.crm.entry.User;
import com.echo.crm.service.UserService;
import com.echo.crm.utils.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yucheng
 * @description
 * @create 2019-12-25 3:28 下午
 */


@Slf4j
@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public ResultInfo<TokenHandler> login(@RequestBody User user) {
        return ResultInfo.createResult(userService.login(user));
    }

    @PostMapping(value = "/logout")
    public ResultInfo<Object> logout() {
        SecurityUtils.getSubject().logout();
        return ResultInfo.createEmptyResult();
    }
}
