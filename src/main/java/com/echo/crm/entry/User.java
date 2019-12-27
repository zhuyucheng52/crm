package com.echo.crm.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:44
 */

@Data
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String sex;
    private Date birthday;
    private String mobile;
    private String address;
    private String email;
    private String username;
    private String password;
    private String avatar;
    private Boolean disabled;
    private Boolean deleted;
    private List<Role> roles;

    private String remark;
    private Long creator = 0L;
    private String tenantId = "0";
}
