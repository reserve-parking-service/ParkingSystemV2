package co.kr.pms.common.custom;

import co.kr.pms.common.auth.AuthRepository;
import co.kr.pms.common.auth.AuthUserDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        AuthUserDto user = authRepository.findById(Long.parseLong(id)).orElseThrow( () -> new UsernameNotFoundException("해당하는 사용자가 없습니다"));
        CustomUserInfoDto dto = modelMapper.map(user, CustomUserInfoDto.class);

        return new CustomUserDetails(dto);
    }
}
