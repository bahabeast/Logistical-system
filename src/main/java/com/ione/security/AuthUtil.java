package com.ione.security;
import com.ione.entity.enums.Role;
import com.ione.service.CustomUserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;
public class AuthUtil {
    public static String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static String getCurrentUserRole() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream()
                .findFirst()
                .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", ""))
                .orElseThrow(() -> new RuntimeException("No role found for current user"));
    }
}
