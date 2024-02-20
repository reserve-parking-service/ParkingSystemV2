package co.kr.pms.common.security;

import lombok.Data;

@Data
public class SecurityUserInfoDto {
    private String userId;
    private String email;
    private String role;
}
