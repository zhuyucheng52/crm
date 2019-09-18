package com.echo.crm.dto;

import lombok.Data;

/**
 * @author yucheng
 * @description
 * @create 2019-09-18 14:32
 */

@Data
public class PasswordModifyDTO {
    private String oldPassword;
    private String newPassword;
}
