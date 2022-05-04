package cybersoft.java16.ecom.user.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import cybersoft.java16.ecom.user.model.Provider;
import cybersoft.java16.ecom.user.model.UserStatus;
import cybersoft.java16.ecom.user.validation.annotation.UniqueUsername;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Builder
public class UserDTO {
	@UniqueUsername(message="{user.username.existed}")
	@NotBlank
	@Length(max = 100, min = 6, message = "{user.username.size}")
	private String username;
	@NotBlank
	@Length(max = 20, min = 6, message = "{user.password.size}")
	private String password;
	private String firstName;
	private String lastName;
	private String fullName;
	private String address;
	private String phoneNumber;
	private UserStatus status;
}
