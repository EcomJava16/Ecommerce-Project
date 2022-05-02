package cybersoft.java16.ecom.security.service;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cybersoft.java16.ecom.user.model.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Service
public class JwtProviderImpl implements JwtProvider{
	
	private final String JWT_SECRET = "secretkey";
	
	private final int JWT_EXPIRATTION = 86400000;
	
	private final String PREFIX = "Bearer";

	public String generateToken(String  username) {
		Date now = new Date();
		Date dateExpired = new Date(now.getTime() + JWT_EXPIRATTION);
		return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(dateExpired)
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
	}
	
	public String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken==null) {
			return null;
		}
			return bearerToken.substring(PREFIX.length(), bearerToken.length());
	}

	@Override
	public boolean validateToken(String jwt) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwt);
			return true;
		} catch (UnsupportedJwtException | ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException  e) {
		}
		return false;
	}

	@Override
	public String getUsernameFromToken(String jwt) {
		return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwt).getBody().getSubject();
	}
}
