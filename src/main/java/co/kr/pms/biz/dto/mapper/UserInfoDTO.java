package co.kr.pms.biz.dto.mapper;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "사용자")
public class UserInfoDTO {

    @Schema(description = "사용자 계정")
    private String usrId;

    @Schema(description = "이름")
    private String usrNm;

}
