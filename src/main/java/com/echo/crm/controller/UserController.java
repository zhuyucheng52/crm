package com.echo.crm.controller;

import com.echo.crm.dto.ResultDTO;
import com.echo.crm.entry.User;
import com.echo.crm.service.UserService;
import com.echo.crm.utils.Page;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResultDTO<User> getUser(@PathVariable("id") Long id) {
        return ResultDTO.createResult(userService.getUserById(id));
    }

    @GetMapping("/users/{page:\\d+}")
    public ResultDTO<Page<User>> getUser(@PathVariable("page") Integer page) {
        PageList<User> users = userService.getUsers(new PageBounds(page, 20));
        return ResultDTO.createResult(new Page(users, users.getPaginator()));
    }

    @PostMapping("/user")
    public ResultDTO<User> addUser(@RequestBody User user) {
        return ResultDTO.createResult(userService.add(user));
    }

    @PutMapping("/user")
    public ResultDTO<User> modifyUser(@RequestBody User user) {
        return ResultDTO.createResult(userService.update(user));
    }

}
