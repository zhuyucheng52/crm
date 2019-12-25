package com.echo.crm.security;

import com.echo.crm.entry.User;
import com.echo.crm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/10/11.
 */
@Slf4j
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        auth.setRoles(userService.findRolesByUsername(username));
        auth.setStringPermissions(userService.findPermissionsByUsername(username));
        return auth;
    }

    /**
     * 认证
     *
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            // 从TOKEN解析不出用户名
            throw new AuthenticationException("请求非法");
        }

        User u = userService.findByUsername(username);
        if (u == null) {
            log.warn("User: {} is not exists", username);
            throw new AuthenticationException("用户不存在");
        }
        int result = JWTUtil.verify(token, username, JWTProperties.SIGN_KEY);
        if (result == JWTUtil.SUCCESS) {
            // 验证TOKEN成功
            ThreadContext.put("token", token);
            return new SimpleAuthenticationInfo(username, token, "myRealm");
        } else if (result == JWTUtil.EXPIRED) {
            // TOKEN过期
            throw new ExpiredCredentialsException("登录超时,请重新登录");
        } else {
            // TOKEN验证失败
            throw new AuthenticationException("用户名或密码不正确");
        }
    }
}
