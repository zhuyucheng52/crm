package com.echo.crm.dto;

import com.echo.crm.entry.Role;
import com.echo.crm.entry.User;
import lombok.Data;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yucheng
 * @description
 * @create 2019-10-31 23:20
 */

@Data
public class UserDTO extends User {
    @Transient
    private List<Role> roles = new ArrayList<>();
}
