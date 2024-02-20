package co.kr.pms.biz.repository.mapper;

import co.kr.pms.biz.dto.mapper.UserInfoDTO;
import co.kr.pms.biz.vo.SearchUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapperRepository {
    public List<Map<String,Object>> getUserList(SearchUserVO param ) throws Exception;

    public int getUserListCount(SearchUserVO searchVo) throws Exception;
}
