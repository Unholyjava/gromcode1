package lessons_core_20_2;

import java.util.Date;

public class Transaction {
	private long id;
	private String city;
	private int amount;
	private String description;
	private TransactionType type;
	private Date dateCreated;
	
	public Transaction(long id, String city, int amount, String description, TransactionType type, Date dateCreated) {
		this.id = id;
		this.city = city;
		this.amount = amount;
		this.description = description;
		this.type = type;
		this.dateCreated = dateCreated;
	}

	public long getId() {
		return id;
	}

	public String getCity() {
		return city;
	}

	public int getAmount() {
		return amount;
	}

	public String getDescription() {
		return description;
	}

	public TransactionType getType() {
		return type;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	@Override
	public boolean equals(Object obj) {
		Transaction tr = (Transaction) obj;
		if (id == tr.id
			&& city.equals(tr.city)
			&& amount == tr.amount
			&& description.equals(tr.description)
			&& type.equals(tr.type)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", city=" + city + ", amount=" + amount + ", description=" + description
				+ ", type=" + type + ", dateCreated=" + dateCreated + "]";
	}
	
}
