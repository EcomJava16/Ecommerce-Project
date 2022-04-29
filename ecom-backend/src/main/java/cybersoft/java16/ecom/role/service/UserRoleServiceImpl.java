package cybersoft.java16.ecom.role.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cybersoft.java16.ecom.common.helper.ResponseHelper;
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
	public List<UserRoleDTO> findAllRoles() {
		List<UserRole> lstRoles = repository.findAll();
		List<UserRoleDTO> lstRoleDto = lstRoles.stream().map(t -> UserRoleMapper.INSTANCE.toDTO(t))
				.collect(Collectors.toList());
		return lstRoleDto;
	}

	@Override
	public UserRoleDTO createNewRole(UserRoleDTO dto) {
		UserRole role = UserRoleMapper.INSTANCE.toRole(dto);
		repository.save(role);
		return UserRoleMapper.INSTANCE.toDTO(role);
	}

	@Override
	public UserRoleDTO updateRole(String roleId, @Valid UserRoleUpdateDTO dto) {
		try {
			UserRole updateRole = repository.getById(UUID.fromString(roleId));
			if (!updateRole.getName().equals(dto.getName())) {
				UserRole existedRole = repository.findByName(dto.getName());
			if (existedRole != null) {
				return null;
			}
			updateRole.setDescription(dto.getDescription());
			}
			repository.save(updateRole);
			return UserRoleMapper.INSTANCE.toDTO(updateRole);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}

	@Override
	public UserRoleDTO deleteRole(String roleId) {
		try {
			UserRole deleterole = repository.getById(UUID.fromString(roleId));
			repository.deleteById(UUID.fromString(roleId));
			return UserRoleMapper.INSTANCE.toDTO(deleterole);
		} catch (EntityNotFoundException  e) {
			return null;
		}
	}

}
