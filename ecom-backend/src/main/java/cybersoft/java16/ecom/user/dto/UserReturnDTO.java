package cybersoft.java16.ecom.user.dto;
import cybersoft.java16.ecom.user.model.UserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserReturnDTO {
	private String username;
	private String firstName;
	private String lastName;
	private String fullName;
	private String address;
	private String phoneNumber;
	private String email;
	private UserStatus status;
}
