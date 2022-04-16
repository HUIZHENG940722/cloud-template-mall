package com.ethan.mall.oauth2.domain;

import com.ethan.mall.common.domain.AuthenticationUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author zhenghui
 * @name AuthorizationUser
 * @Description 授权用户实体
 * @Date 2022/3/12
 */
@Data
@NoArgsConstructor
public class AuthorizationUser implements UserDetails {
    private AuthenticationUser authenticationUser;

    public AuthorizationUser(AuthenticationUser authenticationUser) {
        this.authenticationUser = authenticationUser;
    }

    @Override
    public String getPassword() {
        return this.authenticationUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.authenticationUser.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authenticationUser.getRoles().stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.authenticationUser.getStatus()==1;
    }
}
