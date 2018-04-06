package com.ip.security.util;

import com.ip.domain.Ownable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PermissionValidator {

    public boolean hasPermission(Authentication auth, Ownable resource) {
        return isAdmin(auth) ||
                resource.getOwner() == null ||
                resource.getOwner().equals(auth.getName());
    }

    private boolean isAdmin(Authentication auth) {
        return auth
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
                .contains("ROLE_ADMIN");
    }
}
