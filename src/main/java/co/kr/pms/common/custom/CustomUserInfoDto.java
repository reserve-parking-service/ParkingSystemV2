package co.kr.pms.common.custom;

import co.kr.pms.common.security.RoleType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomUserInfoDto {
    private Long memberId;
    private String email;
    private String name;
    private String password;
    private RoleType roleType;
}
