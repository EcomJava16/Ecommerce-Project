package cybersoft.java16.ecom.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cybersoft.java16.ecom.common.helper.ResponseHelper;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class AuthorizationHandler {
	private final String MESSAGE = "You don't have permission to access this action";

	@ExceptionHandler(UnAuthorizedException.class)
	public Object handlerUnauthorizedException() {
		return ResponseHelper.getErrorResponse(MESSAGE, HttpStatus.UNAUTHORIZED);
	}
}
