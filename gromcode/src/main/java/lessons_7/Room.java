package lessons_7;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ROOMS")
public class Room {
	private long id;
	private int numberOfGuests;
	private double price;
	private int breakfastIncluded;
	private int petsAllowed;
	private Date dateAvailableFrom;
	private Hotel hotel;
	
	@Id
	//@SequenceGenerator (name = "PR_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
	//@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "PR_SEQ")
	@Column (name = "ID")
	public long getId() {
		return id;
	}
	
	@Column (name = "NUMBER_OF_GUESTS")
	public int getNumberOfGuests() {
		return numberOfGuests;
	}
	
	@Column (name = "PRICE")
	public double getPrice() {
		return price;
	}
	
	@Column (name = "BREAKFAST_INCLUDED")
	public int getBreakfastIncluded() {
		return breakfastIncluded;
	}
	
	@Column (name = "PETS_ALLOWED")
	public int getPetsAllowed() {
		return petsAllowed;
	}
	
	@Column (name = "DATE_AVAILABLE_FROM")
	public Date getDateAvailableFrom() {
		return dateAvailableFrom;
	}
	
	@OneToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "ID_HOTELS")
	public Hotel getHotel() {
		return hotel;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @param numberOfGuests the numberOfGuests to set
	 */
	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}
	
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * @param breakfastIncluded the breakfastIncluded to set
	 */
	public void setBreakfastIncluded(int breakfastIncluded) {
		this.breakfastIncluded = breakfastIncluded;
	}
	
	/**
	 * @param petsAllowed the petsAllowed to set
	 */
	public void setPetsAllowed(int petsAllowed) {
		this.petsAllowed = petsAllowed;
	}
	
	/**
	 * @param dateAvailableFrom the dateAvailableFrom to set
	 */
	public void setDateAvailableFrom(Date dateAvailableFrom) {
		this.dateAvailableFrom = dateAvailableFrom;
	}
	
	/**
	 * @param hotel the hotel to set
	 */
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Room [id=" + id + ", numberOfGuests=" + numberOfGuests + ", price=" + price + ", breakfastIncluded="
				+ breakfastIncluded + ", petsAllowed=" + petsAllowed + ", dateAvailableFrom=" + dateAvailableFrom
				+ ", hotel=" + hotel + "]";
	}
}
