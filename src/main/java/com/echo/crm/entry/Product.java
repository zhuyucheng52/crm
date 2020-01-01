package com.echo.crm.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author yucheng
 * @description
 * @create 2019-09-18 14:56
 */

@Data
@Table(name = "tbl_product")
@EqualsAndHashCode(callSuper = false)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "名称不能为空")
    private String name;
    private Long categoryId;
    private ProductCategory category;
    private String remark;
    private Double price;
    private Boolean disabled;
}
