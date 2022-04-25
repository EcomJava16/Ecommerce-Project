package cybersoft.java16.ecom.role.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.java16.ecom.role.dto.UserRoleDTO;
import cybersoft.java16.ecom.role.dto.UserRoleUpdateDTO;
import cybersoft.java16.ecom.role.mapper.UserRoleMapper;
import cybersoft.java16.ecom.role.model.UserGroup;
import cybersoft.java16.ecom.role.model.UserRole;
import cybersoft.java16.ecom.role.repository.UserRoleRepository;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	UserRoleRepository repository;

	@Override
	public List<UserRoleDTO> findAllDto() {
		List<UserRole> lstRoles = repository.findAll();
		List<UserRoleDTO> lstRoleDto = lstRoles.stream()
				.map(t -> UserRoleMapper.INSTANCE.toDTO(t))
				.collect(Collectors.toList());
		return lstRoleDto;
	}

	@Override
	public UserRoleDTO createNewRole(UserRoleDTO dto) {
		UserRole role = UserRoleMapper.INSTANCE.toModel(dto);
		UserRole newRole = repository.save(role);
		return UserRoleMapper.INSTANCE.toDTO(newRole);
	}

	@Override
	public UserRoleDTO updateRole(String roleId, @Valid UserRoleUpdateDTO dto) {
		Optional<UserRole> role = repository.findById(UUID.fromString(roleId));
		
		if(role.isPresent()) {
			if (!role.get().getCode().equals(dto.getCode())) {
		    	Optional<UserRole> existedRole = repository.findByCode(dto.getCode());
		    	if (existedRole.isPresent())
		    		return null;
		    	
		    	role.get().setCode(dto.getCode());
		    }
			if (!role.get().getName().equals(dto.getName())) {
		    	Optional<UserRole> existedRole = repository.findByName(dto.getName());
		    	if (existedRole.isPresent())
		    		return null;
		    	
		    	role.get().setName(dto.getName());
		    }
			role.get().setDescription(dto.getDescription());		
			UserRole updated = repository.save(role.get());
			return UserRoleMapper.INSTANCE.toDTO(updated);
		}
		return null;
	}

	@Override
	public UserRoleDTO deleteRole(String roleId) {
		Optional<UserRole> role = repository.findById(UUID.fromString(roleId));
		
		if(role.isPresent()) {
			repository.deleteById(UUID.fromString(roleId));
			return UserRoleMapper.INSTANCE.toDTO(role.get());
		}
		return null;
	}

}
