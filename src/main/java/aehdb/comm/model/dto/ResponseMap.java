package aehdb.comm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseMap {

	private String response;
	private String message;
	private Object data;
	
}
