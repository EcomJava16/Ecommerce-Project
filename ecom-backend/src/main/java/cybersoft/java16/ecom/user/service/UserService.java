package cybersoft.java16.ecom.user.service;

import java.util.List;


import cybersoft.java16.ecom.user.dto.UserDTO;
import cybersoft.java16.ecom.user.dto.UserUpdateDTO;

public interface UserService {

	List<UserDTO> findAllUser();

	UserDTO createNewUser(UserDTO dto);

	UserDTO updateUser(String userId,UserUpdateDTO dto);

	UserDTO deleteUser(String userId);

}
