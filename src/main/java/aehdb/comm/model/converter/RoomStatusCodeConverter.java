package aehdb.comm.model.converter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoomStatusCodeConverter extends AbstractBaseEnumConverter<RoomStatusCode, String> {

    @Override
    protected RoomStatusCode[] getValueList() {
        return RoomStatusCode.values();
    }
}

