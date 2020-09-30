package com.myclass.elearning.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final SignatureAlgorithm hs512 = SignatureAlgorithm.HS512;
    private static final long JWT_EXPIRATION = 24 * 60 * 60 * 1000L;
    private static final String JWT_SECRET = "EQAXA@12sxwwT";

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private String jwtSecretEncode;

    @PostConstruct
    public void init(){
        jwtSecretEncode = passwordEncoder.encode(JWT_SECRET);
    }

    public JwtTokenProvider(PasswordEncoder passwordEncoder,
                            @Qualifier("userDetailsServiceImp") UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    public String creatToken(Authentication authentication) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + JWT_EXPIRATION);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(hs512, jwtSecretEncode)
                .compact();
    }

    public Authentication authentication(HttpServletRequest request) {
        try {
            String tokenBearer = request.getHeader("Authorization");
            if (tokenBearer != null && tokenBearer.startsWith("Bearer ")) {
                String token = tokenBearer.replace("Bearer ", "");

                Claims body = Jwts.parser()
                        .setSigningKey(jwtSecretEncode)
                        .parseClaimsJws(token)
                        .getBody();

                String email = body.getSubject();
                Date expiration = body.getExpiration();
                Date current = new Date();

                if (current.getTime() < expiration.getTime()) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                }
            }
        } catch (Exception e){
            System.out.println("Truy cập bị từ chối");
        }
        return null;
    }
}
