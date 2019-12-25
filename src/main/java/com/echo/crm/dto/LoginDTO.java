package com.echo.crm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author yucheng
 * @description
 * @create 2019-12-25 8:41 下午
 */

@Data
public class LoginDTO {
    @NotNull(message = "用户名不能为空")
    private String username;
    @Size(min = 6, max = 25, message = "密码长度必须在6 ~ 25之间")
    private String password;
}
