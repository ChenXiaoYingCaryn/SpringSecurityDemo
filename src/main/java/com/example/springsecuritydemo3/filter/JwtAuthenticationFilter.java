package com.example.springsecuritydemo3.filter;

import com.example.springsecuritydemo3.component.JwtTokenProvider;
import com.example.springsecuritydemo3.component.SpringUtils;
import com.example.springsecuritydemo3.dao.UserDao;
import com.example.springsecuritydemo3.service.impl.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于拦截 HTTP 请求，并验证 JWT 令牌
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider = SpringUtils.getBean(JwtTokenProvider.class);

    private UserDetailsServiceImpl userDetailsService = SpringUtils.getBean(UserDetailsServiceImpl.class);

    private UserDao userDao = SpringUtils.getBean(UserDao.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {
            // 1.从 HTTP 请求中获取 JWT 令牌
            String jwt = getJwtFromRequest(httpServletRequest);
            // 2. 从JWT令牌中获取用户信息
            String username = jwtTokenProvider.getUserNameFromToken(jwt);
            // 3. 判断当前令牌是否和数据库令牌一致，防止盗用令牌
            String tokenInDB = userDao.getTokenByUsername(username);
            if(!tokenInDB.equals(jwt)){
                throw new Exception("The JWT token has expired");
            }
            // 4.验证 JWT 令牌的合法性
            if (StringUtils.hasText(jwt) && jwtTokenProvider.isTokenExpired(jwt)) {
                // 5.加载用户信息
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 6.将用户信息设置到 SecurityContextHolder 中
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());;
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    // 从 HTTP 请求中获取 JWT 令牌
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

