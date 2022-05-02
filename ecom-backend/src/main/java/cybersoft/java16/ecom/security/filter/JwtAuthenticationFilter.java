package cybersoft.java16.ecom.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import cybersoft.java16.ecom.security.service.JwtProvider;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtProvider provider;
	@Autowired
	private UserDetailsService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = provider.getJwtFromRequest(request);
		if (StringUtils.hasText(jwt) && provider.validateToken(jwt)) {
			UserDetails user = service.loadUserByUsername(provider.getUsernameFromToken(jwt));
			Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),
					user.getPassword(), user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}
}
