package cybersoft.java16.ecom.security.service;

import javax.servlet.http.HttpServletRequest;

public interface JwtProvider {
String generateToken(String  username);
String getJwtFromRequest(HttpServletRequest request);
boolean validateToken(String jwt);
String getUsernameFromToken (String jwt);
}
