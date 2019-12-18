package com.echo.crm.entry;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * @author yucheng
 * @description
 * @create 2019-09-18 17:49
 */

@Data
@Table(name = "tbl_order")
@EqualsAndHashCode(callSuper = false)
public class Order extends BaseEntry {
    /**
     * 等待审批
     */
    public static final int APPROVE_STATUS_WAIT = 0;

    /**
     * 拒绝审批
     */
    public static final int APPROVE_STATUS_REJECT = 1;

    /**
     * 审批通过
     */
    public static final int APPROVE_STATUS_PASS = 2;

    private String onlineId;
    private Product product;
    @DecimalMin(value = "0.0", message = "实付金额非法")
    private Double payment;
    private Integer status;
    private String address;
    private Long customerId;
    private String customerName;
    private String customerMobile;
    private Long approveId;
    @NotNull(message = "审批状态不能为空")
    private Integer approveStatus;
    private String approveRemark;
    private Integer disabled;
}
