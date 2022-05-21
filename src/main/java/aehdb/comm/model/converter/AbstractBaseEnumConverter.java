package aehdb.comm.model.converter;

import java.util.Arrays;

import javax.persistence.AttributeConverter;

// enum클래스를 상속받으면서 BaseEnumCode로 구현된 클래스로 제한하는 의미, & 는 인터페이스의 구현채다를 의미
// 예를들어 TFCode는 Enum클래스이고, BaseEnumCode를 구현했음
// abstract class로 정의한 이유는 컨버터를 쉽게 계속 작성하려는 의도이며, 당연하게도 T 제네릭을 지정할 수 없다.
// 어찌보면 닭이 먼저냐 달걀이 먼저냐
public abstract class AbstractBaseEnumConverter<X extends Enum<X> & BaseEnumCode<Y>, Y>
		implements AttributeConverter<X, Y> {

	protected abstract X[] getValueList();

	@Override
	public Y convertToDatabaseColumn(X attribute) {
		if (attribute == null) {
			return null;
		} else {
			return attribute.getValue();
		}
	}

	@Override
	public X convertToEntityAttribute(Y dbData) {
		return Arrays.stream(getValueList()).filter(e -> e.getValue().equals(dbData)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported type for %s.", dbData)));
	}
}
