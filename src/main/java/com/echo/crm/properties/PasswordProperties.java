package com.echo.crm.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yucheng
 * @description
 * @create 2019-09-27 17:03
 */

@Data
@ConfigurationProperties(prefix = "user.password.algorithm")
public class PasswordProperties {
    private String name;
    private Integer times;
}
