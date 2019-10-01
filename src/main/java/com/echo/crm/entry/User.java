package com.echo.crm.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:44
 */

@Data
@Table(name = "tbl_user")
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntry {
    @NotNull(message = "用户名不能为空")
    private String name;
    private String sex;
    private Date birthday;
    @NotNull(message = "手机号不能为空")
    private String mobile;
    private String address;
    private String email;
    @NotNull(message = "用户名不能为空")
    private String account;
    @NotNull(message = "密码不能为空")
    private String password;
    @NotNull(message = "状态不能为空")
    private Integer disabled;

    private List<Role> roles;
}
