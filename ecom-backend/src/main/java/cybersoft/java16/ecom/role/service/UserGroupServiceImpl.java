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
import cybersoft.java16.ecom.role.dto.UserGroupWithUserDTO;
import cybersoft.java16.ecom.role.mapper.UserGroupMapper;
import cybersoft.java16.ecom.role.model.UserGroup;
import cybersoft.java16.ecom.role.model.UserRole;
import cybersoft.java16.ecom.role.repository.UserGroupRepository;
import cybersoft.java16.ecom.role.repository.UserRoleRepository;
import cybersoft.java16.ecom.user.model.EcomUser;
import cybersoft.java16.ecom.user.repository.UserRepository;

@Service
public class UserGroupServiceImpl implements UserGroupService {
	@Autowired
	private UserGroupRepository groupRepository;

	@Autowired
	private UserRoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

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
				Optional<UserGroup> existedGroup = groupRepository.findByName(dto.getName());
				if (existedGroup.isPresent()) {
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
	public UserGroupWithRoleDTO addRoleIntoGroupByName(String groupName, String roleName) {
		try {
			Optional<UserGroup> group = groupRepository.findByName(groupName);
			Optional<UserRole> role = roleRepository.findByName(roleName);
			
			if(group.isEmpty() || role.isEmpty())
				return null;
			
			group.get().addRole(role.get());
			groupRepository.save(group.get());
			return UserGroupMapper.INSTANCE.toGroupWithRoleDTO(group.get());
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	@Override
	public UserGroupWithRoleDTO removeRoleFromGroupByName(String groupName, String roleName) {
		try {
			Optional<UserGroup> group = groupRepository.findByName(groupName);
			Optional<UserRole> role = roleRepository.findByName(roleName);
			
			if(group.isEmpty() || role.isEmpty())
				return null;
			
			group.get().removeRole(role.get());
			groupRepository.save(group.get());
			return UserGroupMapper.INSTANCE.toGroupWithRoleDTO(group.get());
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	@Override
	public UserGroupWithUserDTO addUserIntoGroupByName(String groupName, String username) {
		try {
			Optional<UserGroup> group = groupRepository.findByName(groupName);
			Optional<EcomUser> user = userRepository.findByUsername(username);
			
			if(group.isEmpty() || user.isEmpty())
				return null;
			
			group.get().addUser(user.get());
			groupRepository.save(group.get());
			return UserGroupMapper.INSTANCE.toGroupWithUserDTO(group.get());
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	@Override
	public UserGroupWithUserDTO removeUserFromGroupByName(String groupName, String username) {
		try {
			Optional<UserGroup> group = groupRepository.findByName(groupName);
			Optional<EcomUser> user = userRepository.findByUsername(username);
			
			if(group.isEmpty() || user.isEmpty())
				return null;
			
			group.get().removeUser(user.get());
			groupRepository.save(group.get());
			return UserGroupMapper.INSTANCE.toGroupWithUserDTO(group.get());
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

}
