package com.echo.crm.entry;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yucheng
 * @description
 * @create 2019-09-18 14:58
 */

@Data
public class BaseEntry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String remark;
    private Long userId = 0L;
    private Long tenantId = 0L;
}
