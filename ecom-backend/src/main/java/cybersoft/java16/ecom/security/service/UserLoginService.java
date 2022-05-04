package cybersoft.java16.ecom.security.service;

import cybersoft.java16.ecom.security.dto.UserLoginDTO;

public interface UserLoginService {

	String generateToken(UserLoginDTO dto);

}
