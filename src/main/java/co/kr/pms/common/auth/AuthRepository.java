package co.kr.pms.common.auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<AuthUserDto, Long> {
    AuthUserDto findAuthUserDtoByUsrMail(String email) throws Exception;
}
