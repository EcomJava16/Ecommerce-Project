package cybersoft.java16.ecom.role.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import cybersoft.java16.ecom.role.dto.UserRoleDTO;
import cybersoft.java16.ecom.role.model.UserRole;

@Mapper
public interface UserRoleMapper {
	UserRoleMapper INSTANCE = Mappers.getMapper(UserRoleMapper.class);
	
	UserRoleDTO toDTO(UserRole model);
	UserRole toModel(UserRoleDTO dto);
}
