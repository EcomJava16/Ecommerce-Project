package cybersoft.java16.ecom.role.service;

import java.util.List;

import cybersoft.java16.ecom.role.dto.UserGroupDTO;
import cybersoft.java16.ecom.role.dto.UserGroupUpdateDTO;
import cybersoft.java16.ecom.role.dto.UserGroupWithRoleDTO;

public interface UserGroupService {
	List<UserGroupDTO> findAllGroups();
	UserGroupDTO createNewGroup(UserGroupDTO dto);
	UserGroupDTO updateGroup(String groupId,  UserGroupUpdateDTO dto);
	UserGroupDTO deleteGroup(String groupId);
	UserGroupWithRoleDTO addRoleIntoGroupById(String groupId, String roleId);
	UserGroupWithRoleDTO removeRoleFromGroupById(String groupId, String roleId);
}
