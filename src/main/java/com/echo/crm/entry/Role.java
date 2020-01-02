package com.echo.crm.entry;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-10-01 08:15
 */

@Data
@Table(name = "tbl_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer priority;
    private String remark;
    private List<Permission> permissions = new ArrayList<>();
    @Transient
    private Long userId;
}
