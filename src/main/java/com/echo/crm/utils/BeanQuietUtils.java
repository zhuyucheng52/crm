package com.echo.crm.utils;

import com.echo.crm.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

/**
 * @author yucheng
 * @description
 * @create 2019-11-01 00:38
 */

@Slf4j
public class BeanQuietUtils {
    public static void copyProperties(final Object dest, final Object orig) {
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (Exception e) {
            log.warn("Copy bean {} properties to bean {} failure", orig, dest, e);
            throw new SystemException(e);
        }
    }
}
