package co.kr.pms.common.security;

import co.kr.pms.common.util.ConstantsString;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.Dictionary;

@Slf4j
@Component
public class JwtUtil {
    /**
        암호화된 키
     */
    private final Key key;

    /**
     * 토크 파기 타임 - 초
     */
    private final long accessTokenExpTime;

    /**
     * 생성자
     * @param secretKey
     * @param accessTokenExpTime
     */
    public JwtUtil(
                        @Value("${jwt.secret}") String secretKey,
                        @Value("${jwt.expiration_time}") long accessTokenExpTime)
    {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpTime = accessTokenExpTime;
    }

    /**
     * 사용자 정보로 토큰 생성
     * @param member
     * @return
     */
    public String createeAccessToken(SecurityUserInfoDto member) {
        return createToken(member,accessTokenExpTime);
    }

    /**
     * 토큰 생성
     * @param user
     * @param accessTokenExpTime
     * @return
     */
    private String createToken(SecurityUserInfoDto user, long accessTokenExpTime) {
        Claims ciaims = Jwts.claims();
        ciaims.put("userid", user.getUserId());
        ciaims.put("email", user.getEmail());
        ciaims.put("userid", user.getRole());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(accessTokenExpTime);

        return Jwts.builder()
                .setClaims(ciaims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 토큰에서 사용자 정보 추출
     * @param token
     * @return
     */
    public String getUserId(String token) {
        return parseClaims(token).get(ConstantsString.JWT_MEMBER_USER_ID_PREFIX, String.class);
    }

    /**
     * 토큰에서 정보 반환
     * @param token
     * @return
     */
    private Claims parseClaims(String token) {
        try{
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token).getBody();
        } catch( ExpiredJwtException e) {
            return e.getClaims();
        }
    }


    /**
     * jwt 검증
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid jwt token", e);
        } catch ( ExpiredJwtException e) {
            log.info("Expired JWT token", e);
        } catch ( UnsupportedJwtException e) {
            log.info("UnSupported JWT token", e);
        } catch(IllegalArgumentException e) {
            log.info("UnSupported JWT token", e);
        }
        return false;
    }

}
