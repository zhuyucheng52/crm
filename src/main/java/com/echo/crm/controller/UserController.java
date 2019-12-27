package com.echo.crm.controller;

import com.echo.crm.dto.LoginDTO;
import com.echo.crm.dto.PasswordModifyDTO;
import com.echo.crm.dto.TokenHandler;
import com.echo.crm.entry.User;
import com.echo.crm.service.UserService;
import com.echo.crm.utils.Page;
import com.echo.crm.utils.PageUtils;
import com.echo.crm.utils.ResultInfo;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    public ResultInfo<User> findById(@PathVariable("id") Long id) {
        return ResultInfo.createResult(userService.findById(id));
    }

    @GetMapping("/users")
    public ResultInfo<Page<User>> findByKeyword(@RequestParam(value = "page", required = false) Integer page,
                                                @RequestParam(value = "limit", required = false) Integer limit,
                                                @RequestParam(value = "q", required = false) String key) {
        PageList<User> users = userService.findByKeyword(key, PageUtils.createPageBounds(page, limit));
        return ResultInfo.createResult(PageUtils.createPage(users));
    }

    @PostMapping("/user")
    public ResultInfo<Object> add(@Valid @RequestBody User user) {
        userService.add(user);
        return ResultInfo.createEmptyResult();
    }

    @PutMapping("/user")
    public ResultInfo<Object> update(@RequestBody User user) {
    	userService.update(user);
        return ResultInfo.createEmptyResult();
    }

    @DeleteMapping("/user/{id:\\d+}")
    public ResultInfo<Object> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResultInfo.createEmptyResult();
    }

    @PutMapping("/user/password")
    public ResultInfo<Object> updatePassword(@RequestBody PasswordModifyDTO passwordModifyDTO) {
        Long userId = getCurrentUserId();
        userService.updatePassword(userId, passwordModifyDTO.getNewPassword(), passwordModifyDTO.getOldPassword());
        return ResultInfo.createEmptyResult();
    }

    private Long getCurrentUserId() {
        // TODO yucheng
        return null;
    }


    @GetMapping(value = "/user/info")
    public ResultInfo<User> info(@RequestHeader("TOKEN") String token) {
        return ResultInfo.createResult(userService.findByToken(token));
    }

    @PostMapping(value = "/user/login")
    public ResultInfo<TokenHandler> login(@RequestBody LoginDTO loginDTO) {
        return ResultInfo.createResult(userService.login(loginDTO));
    }

    @PostMapping(value = "/user/logout")
    public ResultInfo<Object> logout() {
        SecurityUtils.getSubject().logout();
        return ResultInfo.createEmptyResult();
    }
}
