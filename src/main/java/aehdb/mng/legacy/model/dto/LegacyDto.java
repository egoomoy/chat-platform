package aehdb.mng.legacy.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/*
 * 레거시 시스템 객체를 정의한다.
 * Id, 레거시 코드, 레거시 명 등
 */

@Getter
@Setter
public class LegacyDto {
	private Long id;

	@NotEmpty(message = "errors.required")
	@Size(min = 1, max = 50, message = "errors.range")
	private String lgcyNm;

	@NotEmpty(message = "errors.required")
	@Size(min = 1, max = 50, message = "errors.range")
	private String lgcyCd;
}
