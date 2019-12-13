package com.echo.crm.dto;

import com.echo.crm.entry.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yucheng
 * @description
 * @create 2019-10-31 23:20
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends User {
    @Transient
    private Set<String> roles = new HashSet<>();
    private Set<String> permissions = new HashSet<>();
}
