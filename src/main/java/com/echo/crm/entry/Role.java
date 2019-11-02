package com.echo.crm.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * @author yucheng
 * @description
 * @create 2019-10-01 08:15
 */

@Data
@Table(name = "tbl_role")
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntry {
    private String name;
    private String remark;
    private Long userId;
}
