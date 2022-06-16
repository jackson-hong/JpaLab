package com.lab.community.filter.security;

import com.lab.community.common.security.AuthInfo;
import com.lab.community.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.lab.community.common.type.user.AccountType.isValidAccountType;

@Slf4j
public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final UserService userService;
    private final static String headerKey = "Authorization";


    public AuthorizationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authHeader = request.getHeader(headerKey);

        authorizeHeader(authHeader);

        chain.doFilter(request, response);
    }

    private void authorizeHeader(String authHeader) {
        // header가 없는 경우 - anonymous user
        if(ObjectUtils.isEmpty(authHeader)) return;
        String[] dividedHeader = authHeader.split(" ");

        // 잘못된 헤더값 validation
        if(dividedHeader.length <= 1) return;
        if(!isValidAccountType(dividedHeader[0])) return;

        // Authentication
        UserDetails user = userService.loadUserByUsername(dividedHeader[1]);
        Authentication authentication = new AuthInfo(user.getUsername(), user);
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
