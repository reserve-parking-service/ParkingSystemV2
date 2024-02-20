package co.kr.pms.common.auth;

public interface AuthService {
    public String login(AuthUserDto dto) throws Exception;
}
