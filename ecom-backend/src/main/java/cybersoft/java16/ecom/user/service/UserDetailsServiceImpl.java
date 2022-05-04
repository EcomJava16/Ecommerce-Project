package cybersoft.java16.ecom.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cybersoft.java16.ecom.user.model.EcomUser;
import cybersoft.java16.ecom.user.model.UserDetailsImpl;
import cybersoft.java16.ecom.user.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username){
		Optional<EcomUser> user = repository.findByUsername(username);
		if (user.isEmpty()) {
			 throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(user.get());
	}

}
