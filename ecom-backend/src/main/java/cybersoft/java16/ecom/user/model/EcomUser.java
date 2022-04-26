package cybersoft.java16.ecom.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import cybersoft.java16.ecom.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@Getter
@Setter
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name = "ecom_user")
public class EcomUser extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "username")
	private String username;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "facebook")
	private String facebook;
	@Column(name = "provider")
	@Enumerated(EnumType.STRING)
	private Provider provider;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "display_name")
	private String displayName;
	@Column(name = "address")
	private String address;
	@Column(name = "phone_number")
	private String phoneNumber;

}
