package com.echo.crm;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author yucheng
 * @description
 * @create 2019-09-18 18:29
 */

@Data
@Component
public class SystemProperties {
    private boolean orderNeedApprove = true;
}
