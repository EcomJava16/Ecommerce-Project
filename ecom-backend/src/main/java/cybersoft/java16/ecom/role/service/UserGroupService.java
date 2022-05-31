package cybersoft.java16.ecom.role.service;

import java.util.List;

import cybersoft.java16.ecom.role.dto.UserGroupDTO;
import cybersoft.java16.ecom.role.dto.UserGroupUpdateDTO;
import cybersoft.java16.ecom.role.dto.UserGroupWithRoleDTO;
import cybersoft.java16.ecom.role.dto.UserGroupWithUserDTO;

public interface UserGroupService {
	List<UserGroupDTO> findAllGroups();
	UserGroupDTO createNewGroup(UserGroupDTO dto);
	UserGroupDTO updateGroup(String groupId,  UserGroupUpdateDTO dto);
	UserGroupDTO deleteGroup(String groupId);
	UserGroupWithRoleDTO addRoleIntoGroupByName(String groupName, String roleName);
	UserGroupWithRoleDTO removeRoleFromGroupByName(String groupId, String roleId);
	UserGroupWithUserDTO addUserIntoGroupByName(String groupName, String username);
	UserGroupWithUserDTO removeUserFromGroupByName(String groupName, String username);
}
