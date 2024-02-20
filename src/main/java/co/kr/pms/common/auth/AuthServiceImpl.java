package co.kr.pms.common.auth;

import co.kr.pms.common.security.JwtUtil;
import co.kr.pms.common.security.SecurityUserInfoDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Transactional
@Service("AuthService")
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final AuthRepository authRepository;
    private final PasswordEncoder encoder;
    private final ModelMapper modelMapper;


    @Override
    public String login(AuthUserDto dto) throws Exception {
        String mail = dto.getUsrMail();
        String password = dto.getUsrPw();

        //아래 메세지로 사용자 정보 암호화 처리 한 이후에 계속 처리
        //String encodedPassword = encoder.encode(password);

        AuthUserDto user = authRepository.findAuthUserDtoByUsrMail(mail);
        if (user == null ) {
            throw new UsernameNotFoundException("이메일이 존재하지 않습니다");
        }

        if (!encoder.matches(password, user.getUsrPw())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다");
        }

        SecurityUserInfoDto info = modelMapper.map(user, SecurityUserInfoDto.class);

        String accessToken = jwtUtil.createeAccessToken(info);
        return accessToken;
    }
}
