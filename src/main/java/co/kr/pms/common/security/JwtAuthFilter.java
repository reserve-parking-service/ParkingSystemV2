package co.kr.pms.common.security;

import co.kr.pms.common.custom.CustomUserDetailsService;
import co.kr.pms.common.util.ConstantsString;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static co.kr.pms.common.util.ConstantsString.JWT_TOKEN_BEARER_PREFIX;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService  customUserDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHader = request.getHeader(ConstantsString.JWT_AUTHORIZATION_PREFIX);

        //jwt가 헤더에 있는경우
        if ( authorizationHader != null && authorizationHader.startsWith(JWT_TOKEN_BEARER_PREFIX + " ")) {

            String token = authorizationHader.substring(7);
            //jwt 유효성 검증
            if (   jwtUtil.validateToken (token)) {
                Long userId = Long.valueOf(jwtUtil.getUserId(token));

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(userId.toString());
                if ( userDetails != null ) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // 현재  Request의 Security Context에 접근권한 설정
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
