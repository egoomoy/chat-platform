package aehdb.mng.user.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import aehdb.mng.legacy.model.dto.LegacyDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserDto {

	@Setter
	@Getter
	public static class Item {
		private Long id;
		private String accntId;
		private String password;
		private int orgnId;
		@NotEmpty(message = "errors.required")
		@Size(min = 1, max = 10, message = "errors.range")
		private String userNm;
		private LegacyDto.Item legacy;
	}

	@Getter
	public static class Request {
		private String accntId;
		private String password;
	}

	@Getter
	@Builder
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Response {
		private String accntId;
		private String userNm;
		@JsonIgnore
		private String token;
		@JsonIgnore
		private String refreshJwt;
		private LegacyDto.Item legacy;

	}
}
