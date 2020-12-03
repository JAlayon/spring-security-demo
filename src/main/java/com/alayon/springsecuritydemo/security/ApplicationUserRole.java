package com.alayon.springsecuritydemo.security;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.alayon.springsecuritydemo.security.ApplicationUserPermission.*;
public enum ApplicationUserRole {
    STUDENT(new HashSet<>()),
    ADMIN(Stream.of(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE)
                .collect(Collectors.toCollection(HashSet::new)));

    private final Set<ApplicationUserPermission> permissions;

    private ApplicationUserRole(Set<ApplicationUserPermission> permissions ){
        this.permissions = permissions;
    }
}
