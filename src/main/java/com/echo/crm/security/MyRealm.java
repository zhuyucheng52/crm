package com.echo.crm.security;

import com.echo.crm.cache.TokenCache;
import com.echo.crm.service.UserService;
import com.echo.crm.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
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

    @Autowired
    private TokenCache tokenCache;

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
        String cachedToken = tokenCache.getToken(username);

        if (cachedToken == null) {
            throw new AuthenticationException("Token已过期(Token expired.)！");
        }
        if (!StringUtils.equals(cachedToken, token)) {
            throw new AuthenticationException("Token错误(Token incorrect.)！");
        }

        return new SimpleAuthenticationInfo(username, token, "myRealm");
}
}
