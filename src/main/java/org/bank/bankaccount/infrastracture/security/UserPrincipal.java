package org.bank.bankaccount.infrastracture.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.bank.bankaccount.domain.model.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class UserPrincipal implements UserDetails {

    private Integer clientNumber;
    private String username;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(Client client) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(client.getRoleName().name()));

        return UserPrincipal.builder()
                .clientNumber(client.getClientNumber())
                .username(client.getUsername())
                .email(client.getEmail())
                .authorities(authorities)
                .build();
    }

    public Integer getClientNumber() {
        return clientNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }
}
