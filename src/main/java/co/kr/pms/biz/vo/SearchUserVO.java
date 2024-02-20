package co.kr.pms.biz.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "사용자 검색 모델")
public class SearchUserVO implements Serializable {

    @Schema(description = "사용자 계정 검색 파라미터")
    private String condUsrId;

    @Schema(description = "사용자명 검색 파라미터")
    private String condUsrName;

}
