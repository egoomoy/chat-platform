package aehdb.comm.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMap {

	private String response;
	private String message;
	private Object data;

	public ResponseMap(String response, String message, Object data) {
		this.response = response;
		this.message = message;
		this.data = data;
	}

}
