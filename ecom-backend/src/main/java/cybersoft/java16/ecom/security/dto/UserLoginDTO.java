package cybersoft.java16.ecom.security.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import cybersoft.java16.ecom.security.validation.annotation.UsernameExisted;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class UserLoginDTO {
	@UsernameExisted(message="{login.username.inexisted}")
	@NotBlank
	@Length(max = 100, min = 6, message = "{user.username.size}")
private String username;
	@NotBlank
	@Length(max = 20, min = 6, message = "{user.password.size}")
private String password;
}
