package com.echo.crm.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author yucheng
 * @description
 * @create 2019-09-21 22:03
 */

@Data
@Table(name = "tbl_finance")
@EqualsAndHashCode(callSuper = false)
public class Finance extends BaseEntry {
    private String name;
    @NotNull(message = "财务记录类型不能为空")
    private Integer type;
    private Double payment;
    private Long financeUserId;
    private Long approveId;
    private Integer approveStatus;
    private String approveRemark;
    private Integer disabled;
}
