package com.echo.crm.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class Product extends BaseEntry {
    @NotNull(message = "名称不能为空")
    private String name;
    private Double price;
    private Integer disabled;
}
