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
import cybersoft.java16.ecom.role.mapper.UserGroupMapper;
import cybersoft.java16.ecom.role.model.UserGroup;
import cybersoft.java16.ecom.role.model.UserRole;
import cybersoft.java16.ecom.role.repository.UserGroupRepository;
import cybersoft.java16.ecom.role.repository.UserRoleRepository;

@Service
public class UserGroupServiceImpl implements UserGroupService {
	@Autowired
	private UserGroupRepository repository;
	
	@Autowired
	private UserRoleRepository roleRepository;

	@Override
	public List<UserGroupDTO> findAllDto() {
		List<UserGroup> lstGroups = repository.findAll();
		List<UserGroupDTO> lstDto = lstGroups.stream()
				.map(t->UserGroupMapper.INSTANCE.toDTO(t))
				.collect(Collectors.toList());
		return lstDto;
	}

	@Override
	public UserGroupDTO createNewGroup(UserGroupDTO dto) {
		UserGroup group = UserGroupMapper.INSTANCE.toModel(dto);
		UserGroup newGroup = repository.save(group);
		return UserGroupMapper.INSTANCE.toDTO(newGroup);
	}

	@Override
	public UserGroupDTO update(String groupId, @Valid UserGroupUpdateDTO dto) {
		Optional<UserGroup> group = repository.findById(UUID.fromString(groupId));
		
		if(group.isPresent())
		{
			if (!group.get().getCode().equals(dto.getCode())) {
		    	Optional<UserGroup> existedGroup = repository.findByCode(dto.getCode());
		    	if (existedGroup.isPresent())
		    		return null;
		    	
		    	group.get().setCode(dto.getCode());
		    }
			if (!group.get().getName().equals(dto.getName())) {
		    	Optional<UserGroup> existedGroup = repository.findByName(dto.getName());
		    	if (existedGroup.isPresent())
		    		return null;
		    	
		    	group.get().setName(dto.getName());
		    }
			group.get().setDescription(dto.getDescription());
			UserGroup updated = repository.save(group.get());
			return UserGroupMapper.INSTANCE.toDTO(updated);
		}
		return null;
	}

	@Override
	public UserGroupDTO delete(String groupId) {
		Optional<UserGroup> group = repository.findById(UUID.fromString(groupId));
		
		if(group.isPresent())
		{
			repository.delete(group.get());
			return UserGroupMapper.INSTANCE.toDTO(group.get());
		}
		return null;
	}

	@Override
	public Object addRole(String groupId, String roleId) {
		Optional<UserGroup> group = repository.findById(UUID.fromString(groupId));
		Optional<UserRole> role = roleRepository.findById(UUID.fromString(roleId));
		
		if(group.isEmpty())
			return "Group ID is invalid";
		else if(role.isEmpty())
			return "Role ID is invalid";
		
		group.get().addRole(role.get());
		UserGroup updated = repository.save(group.get());
		return UserGroupMapper.INSTANCE.toDTO(updated);
	}

	@Override
	public Object removeRole(String groupId, String roleId) {
		Optional<UserGroup> group = repository.findById(UUID.fromString(groupId));
		Optional<UserRole> role = roleRepository.findById(UUID.fromString(roleId));
		
		if(group.isEmpty())
			return "Group ID is invalid";
		else if(role.isEmpty())
			return "Role ID is invalid";
		
		group.get().removeRole(role.get());
		UserGroup updated = repository.save(group.get());
		return UserGroupMapper.INSTANCE.toDTO(updated);
	}

}
