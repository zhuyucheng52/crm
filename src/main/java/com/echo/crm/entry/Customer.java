package com.echo.crm.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;

/**
 * @author yucheng
 * @description
 * @create 2019-09-27 10:40
 */

@Data
@Table(name = "tbl_customer")
@EqualsAndHashCode(callSuper = false)
public class Customer extends BaseEntry {
    private Long id;
    private String name;
    private String mobile;
    private String address;
    private Integer disabled;
}
