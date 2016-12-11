package by.moiski.dao.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

import by.moiski.dao.enums.BlackList;
import by.moiski.dao.enums.UserT;

@Entity
@Table(name = "users")
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;
	@Id
	@Column(name = "userID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getUserId() {
		return userId;
	}
	
	@Size(min = 3, max = 10, message = "Login must be between 3 and 10 characters long.")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Login must be alphanumeric without spaces.")
	private String login;
	@Column(name = "login", unique = true)
	public String getLogin() {
		return login;
	}

	@Size(min = 6, max = 45, message = "Password must be between 6 and 45 characters long.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password must be alphanumeric without spaces.")
	private String password;
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Invalid email address.")
	private String email;
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	
	@Size(min = 1, max = 45, message = "Firstname must be between 1 and 45 characters long.")
	@Pattern(regexp = "[a-zA-Zа-яА-ЯёЁ -]+", message = "Firstname name can not have digits.")
	private String firstname;
	@Column(name = "firstName")
	public String getFirstname() {
		return firstname;
	}


    @Size(min = 1, max = 45, message = "Lastname must be between 1 and 45 characters long.")
    @Pattern(regexp = "[a-zA-Zа-яА-ЯёЁ -]+", message = "Lastname name can not have digits.")
	private String lastname;
	@Column(name = "lastName")
	public String getLastname() {
		return lastname;
	}

	private String shippingAddress;
	@Column(name = "shippingAddress")
	public String getShippingAddress() {
		return shippingAddress;
	}

	private BlackList blackList;
	@Enumerated(EnumType.STRING)
	@Column(name = "isInBlackList", columnDefinition = "enum('Y','N')")
	public BlackList getBlackList() {
		return blackList;
	}

	private Date registrDate;
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "registrDate")
	public Date getRegistrDate() {
		return registrDate;
	}

	private UserT role;
	@Enumerated(EnumType.STRING)
	@Column(name = "userT", columnDefinition = "enum('ADMIN','CLIENT')")
	public UserT getRole() {
		return role;
	}
	
	public User() {
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", login=" + login + ", password=" + password + ", email=" + email
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", shippingAddress=" + shippingAddress
				+ ", blackList=" + blackList + ", registrDate=" + registrDate + ", role=" + role + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((blackList == null) ? 0 : blackList.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((registrDate == null) ? 0 : registrDate.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((shippingAddress == null) ? 0 : shippingAddress.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (blackList != other.blackList)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (registrDate == null) {
			if (other.registrDate != null)
				return false;
		} else if (!registrDate.equals(other.registrDate))
			return false;
		if (role != other.role)
			return false;
		if (shippingAddress == null) {
			if (other.shippingAddress != null)
				return false;
		} else if (!shippingAddress.equals(other.shippingAddress))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public void setBlackList(BlackList blackList) {
		this.blackList = blackList;
	}

	public void setRegistrDate(Date registrDate) {
		this.registrDate = registrDate;
	}

	public void setRole(UserT role) {
		this.role = role;
	}

}
