package co.kr.pms.biz.repository.jpa;

import co.kr.pms.biz.dto.Jpa.UsrInfoEntity;
import co.kr.pms.biz.dto.mapper.UserInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserJpaRepository extends JpaRepository<UsrInfoEntity, String> {

    UsrInfoEntity findByUsrId( String useId ) throws Exception;

}
