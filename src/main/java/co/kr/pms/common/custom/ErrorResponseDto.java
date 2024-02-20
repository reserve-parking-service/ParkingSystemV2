package co.kr.pms.common.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponseDto {

    private int errorType;
    private String errorMessage;
    private LocalDateTime errorTime;

}
