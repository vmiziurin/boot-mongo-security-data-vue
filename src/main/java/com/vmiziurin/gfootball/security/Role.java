package com.vmiziurin.gfootball.security;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(new HashSet<>(Arrays.asList(Permission.READ, Permission.WRITE))),
    USER(new HashSet<>(Collections.singletonList(Permission.READ)));

    @Getter
    private final Set<Permission> permissions;

    Role(HashSet<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return permissions.stream().map(p -> new SimpleGrantedAuthority(p.name())).collect(Collectors.toSet());
    }
}
