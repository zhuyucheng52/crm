package com.echo.crm.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
 * @create 2019-12-26 3:58 下午
 */

@Data
@Table(name = "tbl_permission")
@EqualsAndHashCode
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String value;
	private Long parentId;
	private List<Permission> children = new ArrayList<>();
	private String remark;
	@Transient
	private Long roleId;
}
