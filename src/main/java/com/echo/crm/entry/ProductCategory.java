package com.echo.crm.entry;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yucheng
 * @description
 * @create 2019-12-31 10:14 下午
 */

@Data
@Table(name = "tbl_product_category")
public class ProductCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer priority;
	private String remark;
}
