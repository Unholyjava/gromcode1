package lessons_7;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HOTELS")
public class Hotel {
	private long id;
	private String name;
	private String country;
	private String city;
	private String street;
	
	@Id
	//@SequenceGenerator (name = "PR_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
	//@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "PR_SEQ")
	@Column (name = "ID")
	public long getId() {
		return id;
	}
	
	@Column (name = "NAME")
	public String getName() {
		return name;
	}
	
	@Column (name = "COUNTRY")
	public String getCountry() {
		return country;
	}
	
	@Column (name = "CITY")
	public String getCity() {
		return city;
	}
	
	@Column (name = "STREET")
	public String getStreet() {
		return street;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", country=" + country + ", city=" + city + ", street=" + street
				+ "]";
	}
	
	
}