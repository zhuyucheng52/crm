package com.echo.crm.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author yucheng
 * @description
 * @create 2019-12-27 8:42 下午
 */

@Slf4j
public class PasswordUtilTest {

	@Test
	public void encodedPassword() {
		String encodePassword = PasswordUtil.encodedPassword("111111");
		log.info("Encoded password is: {}", encodePassword);
	}
}
