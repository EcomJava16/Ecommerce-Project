package cybersoft.java16.ecom.user.model;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private EcomUser user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getGrantedAuthority(user);
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthority(EcomUser user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		user.getGroups().stream().forEach(t -> authorities.add(new SimpleGrantedAuthority(t.getName())));
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
