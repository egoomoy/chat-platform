package aehdb.comm.model.converter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum RoomStatusCode implements BaseEnumCode<String> {
	OPEN("O"), CLOSED("C"), ASGMT("A"), NULL("");

	private final String value;
}
