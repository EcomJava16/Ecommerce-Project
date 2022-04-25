package cybersoft.java16.ecom.role.service;

import java.util.List;

import javax.validation.Valid;

import cybersoft.java16.ecom.role.dto.UserRoleDTO;
import cybersoft.java16.ecom.role.dto.UserRoleUpdateDTO;

public interface UserRoleService {
	List<UserRoleDTO> findAllDto();
	UserRoleDTO createNewRole(UserRoleDTO dto);
	UserRoleDTO updateRole(String roleId, @Valid UserRoleUpdateDTO dto);
	UserRoleDTO deleteRole(String roleId);
}
