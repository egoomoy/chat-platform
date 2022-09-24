package aehdb.mng.role.model.dto;

import java.util.Set;

import aehdb.mng.role.model.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class RoleDto {

	@Setter
	@Getter
	public static class Item {
		private Long id;
		private String roleNm;
	}

	@Getter
	public static class Request {
		
	}

	@Getter
	@Builder
	public static class Response {
		private Long id;
		private String roleNm;
	}
}
