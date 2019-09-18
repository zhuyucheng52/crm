package com.echo.crm.entry;

import lombok.Data;

import java.util.Date;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:44
 */

@Data
public class User extends BaseEntry {
    private Long id;
    private String name;
    private String sex;
    private Date birthday;
    private String mobile;
    private String address;
    private String email;
    private String remark;
    private String account;
    private String password;
    private Long userId;
}
