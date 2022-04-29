package cybersoft.java16.ecom.role.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import cybersoft.java16.ecom.role.dto.UserGroupDTO;
import cybersoft.java16.ecom.role.dto.UserGroupWithRoleDTO;
import cybersoft.java16.ecom.role.model.UserGroup;

@Mapper
public interface UserGroupMapper {
	UserGroupMapper INSTANCE = Mappers.getMapper(UserGroupMapper.class);
	
	UserGroupDTO toDTO(UserGroup group);
	UserGroup toGroup(UserGroupDTO dto);
	UserGroupWithRoleDTO toGroupWithRoleDTO(UserGroup group);
}
