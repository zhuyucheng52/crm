package com.echo.crm.entry;

import lombok.Data;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author yucheng
 * @description
 * @create 2019-09-18 14:56
 */

@Data
@Table(name = "tbl_product")
public class Product extends BaseEntry {
    @NotNull(message = "产品名称不能为空")
    private String name;
    @NotNull(message = "产品状态不能为空")
    private Integer disabled;
}
