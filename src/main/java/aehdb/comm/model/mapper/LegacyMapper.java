package aehdb.comm.model.mapper;

import org.mapstruct.Mapper;

import aehdb.mng.legacy.model.dto.LegacyDto;
import aehdb.mng.legacy.model.entity.Legacy;

@Mapper(componentModel = "spring")
public interface LegacyMapper extends UpgradeGenericMapper<LegacyDto.Item, Legacy, LegacyDto.Request, LegacyDto.Response> {

}
