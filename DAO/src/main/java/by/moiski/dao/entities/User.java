package by.moiski.dao.entities;

import java.sql.Date;

import by.moiski.dao.BlackList;

public class User {
	
	
	private long userId;
	private String login;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private String shippingAddress;
	private BlackList blackList;
	private Date registrDate;
	private UserT role;

	public User() {
	}

	public User(long userId, String login, String password, String email, String firstname, String lastname,
			String shippingAddress, BlackList blackList, Date registrDate, UserT role) {
		super();
		this.userId = userId;
		this.login = login;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.shippingAddress = shippingAddress;
		this.blackList = blackList;
		this.registrDate = registrDate;
		this.role = role;
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
		result = prime * result + (int) (userId ^ (userId >>> 32));
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
		if (userId != other.userId)
			return false;
		return true;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public BlackList getBlackList() {
		return blackList;
	}

	public void setBlackList(BlackList blackList) {
		this.blackList = blackList;
	}

	public Date getRegistrDate() {
		return registrDate;
	}

	public void setRegistrDate(Date registrDate) {
		this.registrDate = registrDate;
	}

	public UserT getRole() {
		return role;
	}

	public void setRole(UserT role) {
		this.role = role;
	}

}
