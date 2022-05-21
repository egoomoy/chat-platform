package aehdb.mng.board.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
	private Long id;

	@NotEmpty(message = "errors.required")
	@Size(min = 1, max = 50, message = "errors.range")
	private String title;
}
