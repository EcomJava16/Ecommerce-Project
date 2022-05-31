package cybersoft.java16.ecom.user.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import cybersoft.java16.ecom.user.model.UserStatus;
import cybersoft.java16.ecom.user.validation.annotation.UniqueUsername;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateDTO {
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
