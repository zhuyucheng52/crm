package com.echo.crm.entry;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yucheng
 * @description
 * @create 2019-10-01 08:15
 */

@Data
@Table(name = "tbl_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mobile;
    private String address;
    private Boolean disabled;
    private Integer priority;
    private String remark;
}
