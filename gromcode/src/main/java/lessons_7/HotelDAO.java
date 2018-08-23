package lessons_7;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class HotelDAO extends CommonDAO<Hotel> {
	private static final String SELECT_HOTEL_BY_ID = "SELECT * FROM HOTELS WHERE ID = :id";
	
	public Hotel findById(long id) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Hotel> query = session.createNativeQuery(SELECT_HOTEL_BY_ID, Hotel.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (HibernateException e) {
			System.err.println("findById of Hotel is failed");
			System.err.println(e.getMessage());
			throw e;
		} catch (javax.persistence.NoResultException e) {
			System.out.println("Not found Hotel by ID");
		}
		return null;
	}
	
	public Hotel save(Hotel hotel) {
		return super.save(hotel);
	}
	
	public Hotel delete(long id) {
		return super.delete(id);
	}
	
	public Hotel update (Hotel hotel) {
		return super.update(hotel);
	}
}
