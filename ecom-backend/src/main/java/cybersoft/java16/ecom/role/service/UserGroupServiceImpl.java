package cybersoft.java16.ecom.role.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java16.ecom.role.dto.UserGroupDTO;
import cybersoft.java16.ecom.role.dto.UserGroupUpdateDTO;
import cybersoft.java16.ecom.role.dto.UserGroupWithRoleDTO;
import cybersoft.java16.ecom.role.mapper.UserGroupMapper;
import cybersoft.java16.ecom.role.model.UserGroup;
import cybersoft.java16.ecom.role.model.UserRole;
import cybersoft.java16.ecom.role.repository.UserGroupRepository;
import cybersoft.java16.ecom.role.repository.UserRoleRepository;

@Service
public class UserGroupServiceImpl implements UserGroupService {
	@Autowired
	private UserGroupRepository groupRepository;

	@Autowired
	private UserRoleRepository roleRepository;

	@Override
	public List<UserGroupDTO> findAllGroups() {
		return groupRepository.findAll().stream().map(t -> UserGroupMapper.INSTANCE.toDTO(t))
				.collect(Collectors.toList());
	}

	@Override
	public UserGroupDTO createNewGroup(UserGroupDTO dto) {
		UserGroup group = UserGroupMapper.INSTANCE.toGroup(dto);
		groupRepository.save(group);
		return UserGroupMapper.INSTANCE.toDTO(group);
	}

	@Override
	public UserGroupDTO updateGroup(String groupId, UserGroupUpdateDTO dto) {
		try {
			UserGroup group = groupRepository.getById(UUID.fromString(groupId));
			if (!group.getName().equalsIgnoreCase(dto.getName())) {
				UserGroup existedGroup = groupRepository.findByName(dto.getName());
				if (existedGroup != null) {
					return null;
				}
				group.setName(dto.getName());
			}
			group.setDescription(dto.getDescription());
			return UserGroupMapper.INSTANCE.toDTO(groupRepository.save(group));
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	@Override
	public UserGroupDTO deleteGroup(String groupId) {
		try {
			UserGroup group = groupRepository.getById(UUID.fromString(groupId));
			groupRepository.delete(group);
			return UserGroupMapper.INSTANCE.toDTO(group);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	@Override
	public UserGroupWithRoleDTO addRoleIntoGroupById(String groupId, String roleId) {
		try {
			UserGroup group = groupRepository.getById(UUID.fromString(groupId));
			UserRole role = roleRepository.getById(UUID.fromString(roleId));
			group.addRole(role);
			groupRepository.save(group);
			return UserGroupMapper.INSTANCE.toGroupWithRoleDTO(group);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	@Override
	public UserGroupWithRoleDTO removeRoleFromGroupById(String groupId, String roleId) {
		try {
			UserGroup group = groupRepository.getById(UUID.fromString(groupId));
			UserRole role = roleRepository.getById(UUID.fromString(roleId));
			group.removeRole(role);
			groupRepository.save(group);
			return UserGroupMapper.INSTANCE.toGroupWithRoleDTO(group);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

}
