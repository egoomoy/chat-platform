package aehdb.mng.board.model.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchDto {

	@NotEmpty(message="errors.required")
	@Length(min=1, max=100, message="errors.range")
	private String searchTitle = "";
}
