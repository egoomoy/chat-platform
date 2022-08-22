package aehdb.mng.legacy.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * 레거시 시스템 객체를 정의한다.
 * Id, 레거시 코드, 레거시 명 등
 */

public class LegacyDto {
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Item {
		private Long id;
		@NotEmpty(message = "errors.required")
		@Size(min = 1, max = 50, message = "errors.range")
		private String lgcyNm;
		@NotEmpty(message = "errors.required")
		@Size(min = 1, max = 50, message = "errors.range")
		private String lgcyCd;
	}

	@Getter
	public static class Request {
		private Long id;
		@NotEmpty(message = "errors.required")
		@Size(min = 1, max = 50, message = "errors.range")
		private String lgcyNm;
		@NotEmpty(message = "errors.required")
		@Size(min = 1, max = 50, message = "errors.range")
		private String lgcyCd;
	}

	@Getter
	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Response {
		private Long id;
		@NotEmpty(message = "errors.required")
		@Size(min = 1, max = 50, message = "errors.range")
		private String lgcyNm;
		@NotEmpty(message = "errors.required")
		@Size(min = 1, max = 50, message = "errors.range")
		private String lgcyCd;
	}
}
