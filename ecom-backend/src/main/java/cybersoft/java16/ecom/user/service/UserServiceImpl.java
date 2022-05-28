package cybersoft.java16.ecom.user.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cybersoft.java16.ecom.user.dto.UserDTO;
import cybersoft.java16.ecom.user.dto.UserReturnDTO;
import cybersoft.java16.ecom.user.dto.UserUpdateDTO;
import cybersoft.java16.ecom.user.mapper.UserMapper;
import cybersoft.java16.ecom.user.model.EcomUser;
import cybersoft.java16.ecom.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<UserDTO> findAllUser() {
		return  repository.findByDeleteFalse().stream().map(t -> UserMapper.INSTANCE.toUserDto(t)).collect(Collectors.toList());
	}

	@Override
	public UserDTO createNewUser(UserDTO dto) {
		// Encode before map
		EcomUser user = UserMapper.INSTANCE.toUser(dto);
		user.setPassword(encoder.encode(dto.getPassword()));

		repository.save(user);

		// Set password null before return
		UserDTO newUserDto = UserMapper.INSTANCE.toUserDto(user);
		newUserDto.setPassword(null);
		return newUserDto;
	}

	@Override
	public UserDTO updateUser(String userId, UserUpdateDTO dto) {
		try {
			EcomUser user = repository.getById(UUID.fromString(userId));
			user.setPassword(encoder.encode(dto.getPassword()));
			user.setAddress(dto.getAddress());
			user.setFullName(dto.getFullName());
			user.setFirstName(dto.getFirstName());
			user.setLastName(dto.getLastName());
			user.setPhoneNumber(dto.getPhoneNumber());
			repository.save(user);
			UserDTO updateUserDto = UserMapper.INSTANCE.toUserDto(user);
			updateUserDto.setPassword(null);
			return updateUserDto;
		} catch (EntityNotFoundException | IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public UserDTO deleteUser(String userId) {
		try {
			EcomUser user = repository.getById(UUID.fromString(userId));
			user.setDelete(true);
			repository.save(user);
			UserDTO deleteUserDto = UserMapper.INSTANCE.toUserDto(user);
			deleteUserDto.setPassword(null);
			return deleteUserDto;
		} catch (EntityNotFoundException | IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public UserReturnDTO findUserByUsername(String username) {
		return repository.findByUsername(username).map(t -> UserMapper.INSTANCE.toUserReturnDTO(t)).get();
	}

}
