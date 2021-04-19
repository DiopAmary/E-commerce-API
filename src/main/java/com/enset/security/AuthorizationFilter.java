package com.enset.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    
	
	
    public AuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
     } 
    
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        
        String header = req.getHeader(SecurityConstants.HEADER_STRING);
        
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }   
    
    
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        
        if (token != null) {
            
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
            
            final JwtParser jwtParser = Jwts.parser().setSigningKey(SecurityConstants.TOKEN_SECRET);

            final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

            final Claims claims = claimsJws.getBody();

            final Collection<? extends GrantedAuthority> authorities =
                    Arrays.stream(claims.get("roles").toString().split(","))
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
            String user = claims.getSubject();
        
            System.out.println("authorization => **********" + authorities);
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, "",authorities);
            }
            
            return null;
        }
        
        return null;
    }
    

}