package lessons_8;

import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class HotelDAO extends CommonDAO<Hotel> implements DAO<Hotel>{
	public HotelDAO(Class<Hotel> classCurrent) {
		super(classCurrent);
	}

	private static final String SELECT_HOTEL_BY_NAME = "SELECT * FROM HOTELS WHERE NAME = :nameOrCity";
	private static final String SELECT_HOTEL_BY_CITY = "SELECT * FROM HOTELS WHERE CITY = :nameOrCity";
	
	public Hotel findById(long id) {
		return super.findById(id);
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
	
	public List<Hotel> findByNameOrCity(String nameOrCity, String chooseBy) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Hotel> query = null;
			switch (chooseBy) {
				case "Name": 
					query = session.createNativeQuery(SELECT_HOTEL_BY_NAME, Hotel.class);
					break;
				case "City": 
					query = session.createNativeQuery(SELECT_HOTEL_BY_CITY, Hotel.class);
					break;
			}
			query.setParameter("nameOrCity", nameOrCity);
			return query.getResultList();
		} catch (HibernateException e) {
			System.err.println("findBy" + nameOrCity + " of Hotel is failed");
			System.err.println(e.getMessage());
			throw e;
		} catch (NoResultException e) {
			System.out.println("Not found Hotel by " + nameOrCity);
		}
		return null;
	}
		
}



