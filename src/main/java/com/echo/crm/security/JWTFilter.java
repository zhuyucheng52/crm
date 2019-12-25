package com.echo.crm.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {
    private Set<String> anonymousUrls = new HashSet<>();

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        return true;
    }

    /**
     *
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader(JWTProperties.TOKEN_NAME);

        JWTToken token = new JWTToken(authorization);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登入成功，返回true

        ((HttpServletResponse)response).addHeader(JWTProperties.TOKEN_NAME, JWTUtil.newTokenIfNecessary(authorization));
        return true;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        if (!isAnonymousUrls(httpServletRequest.getRequestURI())) {
//            try {
//                 return executeLogin(request, response);
//            } catch (ExpiredCredentialsException e) {
//                log.warn("Host: {} token expired", request.getRemoteHost());
//                processException(response, HttpStatus.GONE, e.getMessage());
//            } catch (AuthenticationException e) {
//                log.warn("Host: {} login failure", request.getRemoteHost());
//                processException(response, HttpStatus.UNAUTHORIZED, e.getMessage());
//            }
//            return false;
//        }
//        String token = (String) ThreadContext.get("token");
//        ((HttpServletResponse)response).addHeader(JWTProperties.TOKEN_NAME, token);
        return true;
    }

    private boolean isAnonymousUrls(String contextPath) {
        return anonymousUrls.contains(contextPath);
    }

    /**
     * 将非法请求跳转到 /401
     */
    private void processException(ServletResponse resp, HttpStatus status, String msg) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
        try {
            log.warn("Invalid request with msg: {}", msg);
            httpServletResponse.sendError(status.value(), msg);
        } catch (IOException e) {
            log.warn("Send error message failure", e);
        }
    }

    public Set<String> getAnonymousUrls() {
        return anonymousUrls;
    }

    public void setAnonymousUrls(Set<String> anonymousUrls) {
        this.anonymousUrls = anonymousUrls;
    }
}
