package com.lab.community.common.security;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class AuthInfo extends AbstractAuthenticationToken {

    private String accountId;
    private UserDetails userDetails;


    public AuthInfo(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public AuthInfo(String accountId, UserDetails userDetails){
        super(userDetails.getAuthorities());
        this.accountId = accountId;
        this.userDetails = userDetails;
    }

    @Override
    public Object getCredentials() {
        return userDetails.getUsername();
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }
}
