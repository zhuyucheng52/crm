package com.echo.crm.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yucheng
 * @description
 * @create 2019-09-27 17:03
 */

@Data
@Component
@ConfigurationProperties(prefix = "user.password.algorithm")
public class PasswordProperties {
    private String name;
    private Integer times;
}
