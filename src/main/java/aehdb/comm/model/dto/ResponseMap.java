package aehdb.comm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseMap {
	private int status;
	private String message;
	private Object body;
	
}
