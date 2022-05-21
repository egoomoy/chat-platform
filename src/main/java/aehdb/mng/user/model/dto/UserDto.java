package aehdb.mng.user.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	private Long id;
	private String accntId;
	private String password;
	private int orgnId;
	@NotEmpty(message = "errors.required")
	@Size(min = 1, max = 10, message = "errors.range")
	private String userNm;
}
