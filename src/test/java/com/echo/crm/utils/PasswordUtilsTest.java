package com.echo.crm.utils;

import com.echo.crm.properties.PasswordProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author yucheng
 * @description
 * @create 2019-09-27 17:11
 */

@Slf4j
public class PasswordUtilsTest {

    @Test
    public void encoding() throws UnsupportedEncodingException {
        PasswordProperties p = new PasswordProperties();
        p.setName("MD5");
        p.setTimes(4);

        String password = PasswordUtils.encoding(p, "123", "0");
        log.info("Password is: {}", password);
    }
}
