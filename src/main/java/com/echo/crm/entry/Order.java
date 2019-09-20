package com.echo.crm.entry;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * @author yucheng
 * @description
 * @create 2019-09-18 17:49
 */

@Data
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
    @NotNull(message = "产品ID不能为空")
    private Long productId;
    @PositiveOrZero(message = "产品数量非法")
    private Integer productNum;
    @DecimalMin(value = "0.0", message = "实付金额非法")
    private Double payment;
    private Integer status;
    private String address;
    private Long customerId;
    private String customerName;
    private String customerMobile;
    private Long approverId;
    private Integer approveStatus;
    private String approverRemark;
}
