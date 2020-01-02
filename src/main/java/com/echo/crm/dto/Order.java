package com.echo.crm.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yucheng
 * @description
 * @create 2019-09-18 17:49
 */

@Data
@Table(name = "tbl_order")
@EqualsAndHashCode(callSuper = false)
public class Order {
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String num;
    private Long productId;
    private String productName;
    private Integer productNum;
    private Double payment;
    private Integer status;
    private String address;
    private String remark;
    private Long customerId;
    private String customerName;
    private String customerMobile;
    private Integer approveStatus;
    private Integer disabled;
}
