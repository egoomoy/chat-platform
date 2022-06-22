package aehdb.comm.model.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter

public class BaseResponse {
	private int code;
	private String message;

}
