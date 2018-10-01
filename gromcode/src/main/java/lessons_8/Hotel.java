package lessons_8;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HOTELS")
public class Hotel {
	private long id;
	private String name;
	private String country;
	private String city;
	private String street;
	private List<Room> rooms;
	
	@Id
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "hotel")
	public List<Room> getRooms() {
		return rooms;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", country=" + country + ", city=" + city + ", street=" + street
				+ "]";
	}
	
}
