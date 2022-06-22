package aehdb.comm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
	//하단에서 에러코드를 정의
	UNAUTHORIZED(401, "UNAUTHORIZED"),
	NOT_FOUND(404,"PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500,"INTER SERVER ERROR"),
    BAD_REQUEST(400,"BAD_REQUEST"),
    ;

    private int code;
    private String message;
}