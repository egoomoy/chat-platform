package aehdb.comm.model.mapper;

import org.mapstruct.Mapper;

import aehdb.mng.role.model.dto.RoleDto;
import aehdb.mng.role.model.entity.Role;
@Mapper(componentModel = "spring")
public interface RoleMapper extends UpgradeGenericMapper<RoleDto.Item, Role, RoleDto.Request, RoleDto.Response> {

	
}


