package co.kr.pms.biz.service;

import co.kr.pms.biz.dto.Jpa.UsrInfoEntity;
import co.kr.pms.biz.dto.mapper.UserInfoDTO;
import co.kr.pms.biz.vo.SearchUserVO;

import java.util.List;
import java.util.Map;

public interface UserService {
    public UsrInfoEntity getUserInfo(SearchUserVO searchVo) throws Exception;

    public List<Map<String,Object>> getUserList(SearchUserVO searchVo) throws Exception;

    public int getUserListCount(SearchUserVO searchUserInfo)  throws Exception;
}
