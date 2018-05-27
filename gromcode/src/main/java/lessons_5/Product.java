package lessons_5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {

	private long id;
	private String name;
	private String description;
	private int price;
	
	@Id
	@Column (name = "ID")
	public long getId() {
		return id;
	}
	
	@Column (name = "NAME")
	public String getName() {
		return name;
	}
	
	@Column (name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	
	@Column (name = "PRICE")
	public int getPrice() {
		return price;
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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
