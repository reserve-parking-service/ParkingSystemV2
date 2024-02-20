package co.kr.pms.biz.controller;

import co.kr.pms.biz.dto.Jpa.UsrInfoEntity;
import co.kr.pms.biz.dto.mapper.UserInfoDTO;
import co.kr.pms.biz.service.UserService;
import co.kr.pms.biz.vo.SearchUserVO;
import co.kr.pms.common.util.CamelHashMap;
import co.kr.pms.common.util.ReturnType;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Tag(name="사용자 API", description = "사용자 정보 조회 API")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/userInfo")
    public ResponseEntity<UsrInfoEntity> userInfo (@Valid @RequestBody SearchUserVO searchUserInfo ) throws Exception {
        UsrInfoEntity userInfo = userService.getUserInfo(searchUserInfo);
        return ResponseEntity.status(HttpStatus.OK).body(userInfo);
    }

    @PostMapping("/userList")
    public ResponseEntity<Map<String,Object>> userList (@Valid @RequestBody SearchUserVO searchUserInfo ) {
        Map<String,Object> rtn = new HashMap<>();

        try {
            List<Map<String, Object>> list = userService.getUserList(searchUserInfo);
            int count = userService.getUserListCount(searchUserInfo);

            rtn.put(ReturnType.IF_CD, ReturnType.RESULT_MSG_SUCCESS);
            rtn.put(ReturnType.IF_NUMBER, ReturnType.RESULT_MSG_SUCCESS_NUMBER);

            Map<String,Object> data = new HashMap<>();
            data.put(ReturnType.RESULT_TYPE_LIST, list);
            data.put(ReturnType.RESULT_TYPE_COUNT, count);

            rtn.put(ReturnType.IF_DATA, data);
        } catch (Exception e) {
            rtn.put(ReturnType.IF_CD, ReturnType.RESULT_MSG_FAIL);
            rtn.put(ReturnType.IF_NUMBER, ReturnType.RESULT_MSG_FAIL_NUMBER);
            rtn.put(ReturnType.IF_MSG, e.getLocalizedMessage());
        }
        return ResponseEntity.ok(rtn);
     }




}
