package com.echo.crm.dto;

import lombok.Data;

/**
 * @author yucheng
 * @description
 * @create 2019-12-11 11:03
 */

@Data
public class TokenHandler {
    private String token;
    public TokenHandler(String token) {
        this.token = token;
    }
}
