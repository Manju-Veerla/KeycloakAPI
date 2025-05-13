package com.application.auth.config;

import org.apache.commons.collections4.CollectionUtils;
import org.jspecify.annotations.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;

    public JwtAuthConverter() {
        this.jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    }

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        System.out.println("Converting JWT to Authentication Token...");
        final Set<GrantedAuthority> authorities = Stream.concat(
                jwtGrantedAuthoritiesConverter.convert(jwt).stream(),
                Stream.concat(extractUserRoles(jwt).stream(), extractResourceRoles(jwt).stream())
        ).collect(Collectors.toSet());

        return new JwtAuthenticationToken(jwt, authorities);
    }

    private Set<? extends GrantedAuthority> extractUserRoles(Jwt jwt) {
        System.out.println("Extracting user roles from JWT...");
        final Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        System.out.println("Realm access claim: " + realmAccess);
        final List<String> realmRoles = (List<String>) realmAccess.get("roles");
        System.out.println("Realm roles: " + realmRoles);

        if (CollectionUtils.isNotEmpty(realmRoles)) {
            return realmRoles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                    .collect(Collectors.toSet());
        }

        return Collections.emptySet();
    }

    private Set<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        final Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        List<String> resourceRoles = new ArrayList<>();
        for(Map.Entry<String, Object> client : resourceAccess.entrySet()){
            resourceRoles.addAll((List<String>)((Map) client.getValue()).get("roles"));
        }
        if (CollectionUtils.isNotEmpty(resourceRoles)) {
            return resourceRoles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                    .collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }
}