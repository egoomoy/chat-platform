package aehdb.comm.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int status ;
    private String message;

    public ErrorResponse(ErrorCode errorCode){
        this.status = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}