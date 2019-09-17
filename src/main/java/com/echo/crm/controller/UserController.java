package com.echo.crm.controller;

import com.echo.crm.dto.ResultDTO;
import com.echo.crm.entry.User;
import com.echo.crm.service.UserService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id:\\d+}")
    public ResultDTO<User> getUser(@PathVariable("id") Long id) {
        User u = userService.getUserById(id);
        return new ResultDTO<>(u);
    }

    @GetMapping("/users/{page:\\d+}")
    public ResultDTO<List<User>> getUser(@PathVariable("page") Integer page) {
        List<User> users = userService.getUsers(new PageBounds(page, 20));
        return new ResultDTO<>(users);
    }

}
