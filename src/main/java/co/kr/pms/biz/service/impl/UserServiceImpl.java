package co.kr.pms.biz.service.impl;

import co.kr.pms.biz.dto.Jpa.UsrInfoEntity;
import co.kr.pms.biz.repository.jpa.UserJpaRepository;
import co.kr.pms.biz.repository.mapper.UserMapperRepository;
import co.kr.pms.biz.service.UserService;
import co.kr.pms.biz.vo.SearchUserVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private UserJpaRepository repUser;
    private UserMapperRepository repUserMapper;

    /**
     * 사용자 명 조회
     * @param searchVo
     * @return
     * @throws Exception
     */
    @Override
    public UsrInfoEntity getUserInfo(SearchUserVO searchVo) throws Exception {
        return repUser.findByUsrId(searchVo.getCondUsrId());
    }

    @Override
    public List<Map<String, Object>> getUserList(SearchUserVO searchVo) throws Exception {
        return repUserMapper.getUserList(searchVo);
    }

    @Override
    public int getUserListCount(SearchUserVO searchVo) throws Exception {
        return repUserMapper.getUserListCount(searchVo);
    }

}
