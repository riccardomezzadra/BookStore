package com.controller;

import com.contract.data.IAuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationFilter implements Filter {

    private final static Logger log = LoggerFactory.getLogger(AuthenticationFilter.class.getName());

    @Autowired
    private IAuthenticationService authenticationService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Initializing filter : " + this);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String token = req.getHeader("Authorization");
        log.info(req.getMethod() + " " + req.getRequestURI());
        if ("OPTIONS".equals(req.getMethod()) || token != null) {
            log.info("Token: " + token);
            if (authenticationService.checkToken(token))
                chain.doFilter(request, response);
        } else {
            log.info("Token: " + token);
            log.info("NOT ALLOWED");
        }
    }

    @Override
    public void destroy() {
        log.info("Destructing filter : " + this);
    }
}
