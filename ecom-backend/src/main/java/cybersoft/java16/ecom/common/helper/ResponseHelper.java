package cybersoft.java16.ecom.common.helper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ResponseHelper {
	public static ResponseEntity<Object> getResponse(Object obj, HttpStatus status) {
		Map<String, Object> map = new HashMap<>();
		map.put("hasErrors", false);
		map.put("content", obj);
		map.put("error", "");
		map.put("timeStamp", LocalDateTime.now());
		map.put("status", status);
		return new ResponseEntity<Object>(map, status);
	}

	public static ResponseEntity<Object> getResponse(String accept, HttpStatus status) {
		Map<String, Object> map = new HashMap<>();
		map.put("hasErrors", false);
		map.put("content", accept);
		map.put("error", "");
		map.put("timeStamp", LocalDateTime.now());
		map.put("status", status);
		return new ResponseEntity<Object>(map, status);
	}

	public static ResponseEntity<Object> getErrorResponse(BindingResult result, HttpStatus status) {
		Map<String, Object> map = new HashMap<>();
		map.put("hasErrors", true);
		map.put("content", "");
		map.put("error", ErrorHelper.getAllError(result));
		map.put("timeStamp", LocalDateTime.now());
		map.put("status", status);
		return new ResponseEntity<Object>(map, status);
	}

	public static ResponseEntity<Object> getErrorResponse(String error, HttpStatus status) {
		Map<String, Object> map = new HashMap<>();
		map.put("hasErrors", true);
		map.put("content", "");
		map.put("error", error);
		map.put("timeStamp", LocalDateTime.now());
		map.put("status", status);
		return new ResponseEntity<Object>(map, status);
	}
}
