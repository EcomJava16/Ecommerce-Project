package cybersoft.java16.ecom.role.service;

import java.util.List;

import javax.validation.Valid;

import cybersoft.java16.ecom.role.dto.UserGroupDTO;
import cybersoft.java16.ecom.role.dto.UserGroupUpdateDTO;

public interface UserGroupService {
	List<UserGroupDTO> findAllDto();
	UserGroupDTO createNewGroup(UserGroupDTO dto);
	UserGroupDTO update(String groupId, @Valid UserGroupUpdateDTO dto);
	UserGroupDTO delete(String groupId);
	Object addRole(String groupId, String roleId);
	Object removeRole(String groupId, String roleId);
}
