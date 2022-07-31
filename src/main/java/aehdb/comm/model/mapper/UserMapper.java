
package aehdb.comm.model.mapper;

import org.mapstruct.Mapper;

import aehdb.mng.user.model.dto.UserDto;
import aehdb.mng.user.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends UpgradeGenericMapper<UserDto.Item, User, UserDto.Request, UserDto.Response> {

}
