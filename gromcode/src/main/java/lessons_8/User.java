package lessons_8;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
	private long id;
	private String userName;
	private String password;
	private String country;
	private UserType userType;
	private List<Order> orders;
	
	@Id
	@SequenceGenerator (name = "PR_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "PR_SEQ")
	@Column (name = "ID")
	public long getId() {
		return id;
	}

	@Column (name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	@Column (name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	@Column (name = "COUNTRY")
	public String getCountry() {
		return country;
	}

	@Column (name = "USER_TYPE")
	public UserType getUserType() {
		return userType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userOrdered")
	public List<Order> getOrders() {
		return orders;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", country=" + country
				+ ", userType=" + userType + "]";
	}
	
}
