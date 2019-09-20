package com.echo.crm.entry;

import lombok.Data;

/**
 * @author yucheng
 * @description
 * @create 2019-09-18 14:58
 */

@Data
public class BaseEntry {
    private Long id;
    private String remark;
    private Long tenantId;
    private Long userId;
}
