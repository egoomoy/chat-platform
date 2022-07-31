package aehdb.comm.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int resCode ;
    private String message;

    public ErrorResponse(ErrorCode errorCode){
        this.resCode = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}