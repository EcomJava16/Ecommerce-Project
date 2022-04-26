package cybersoft.java16.ecom.user.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import cybersoft.java16.ecom.user.dto.UserDTO;
import cybersoft.java16.ecom.user.model.EcomUser;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	EcomUser toUser(UserDTO dto);
	UserDTO toUserDto(EcomUser user);
}
