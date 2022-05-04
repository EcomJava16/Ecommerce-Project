package cybersoft.java16.ecom.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cybersoft.java16.ecom.security.dto.UserLoginDTO;
import cybersoft.java16.ecom.user.model.EcomUser;
import cybersoft.java16.ecom.user.repository.UserRepository;

@Service
public class UserLoginServiceImpl implements UserLoginService{
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public String generateToken(UserLoginDTO dto) {
		Optional<EcomUser> user = repository.findByUsername(dto.getUsername());
		if (encoder.matches(dto.getPassword(), user.get().getPassword())) {
			return jwtProvider.generateToken(user.get().getUsername());
		}
		return null;
	}
}
