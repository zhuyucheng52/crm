package com.echo.crm.entry;

import lombok.Data;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:44
 */

@Data
@Table(name = "tbl_after_sale")
public class AfterSale extends BaseEntry {
    private Long id;
    @NotNull(message = "订单ID不能为空")
    private Long orderId;
    @NotNull(message = "名称不能为空")
    private String name;
    @PositiveOrZero(message = "金额不能为负数")
    private Double payment;
    private Long approveId;
    @NotNull(message = "审批状态不能为空")
    private Integer approveStatus;
    private String approveRemark;

}
