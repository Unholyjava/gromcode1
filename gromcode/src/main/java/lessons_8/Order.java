package lessons_8;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {
	private long id;
	private User userOrdered;
	private Room room;
	private Date dateFrom;
	private Date dateTo;
	private double moneyPaid;
	
	@Id
	@SequenceGenerator (name = "PR_SEQ", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "PR_SEQ")
	@Column (name = "ID")
	public long getId() {
		return id;
	}
	
	@ManyToOne
    @JoinColumn(name = "ID_USERS", nullable = false)
	public User getUserOrdered() {
		return userOrdered;
	}
	
	@OneToOne (targetEntity = Room.class, fetch = FetchType.EAGER)
	@JoinColumn (name = "ROOM_ID")
	public Room getRoom() {
		return room;
	}
	
	@Column (name = "DATE_FROM")
	public Date getDateFrom() {
		return dateFrom;
	}
	
	@Column (name = "DATE_TO")
	public Date getDateTo() {
		return dateTo;
	}
	
	@Column (name = "MONEY_PAID")
	public double getMoneyPaid() {
		return moneyPaid;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUserOrdered(User userOrdered) {
		this.userOrdered = userOrdered;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public void setMoneyPaid(double moneyPaid) {
		this.moneyPaid = moneyPaid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Order [id=" + id + ", userID=" + userOrdered.getId() + ", room =" + room.getId() + ", dateFrom=" + dateFrom
				+ ", dateTo=" + dateTo + ", moneyPaid=" + moneyPaid + "]";
	}
	
	
	
}
