package lessons_8;

import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class RoomDAO extends CommonDAO<Room> implements DAO<Room> {
	private static final String SELECT_ROOM_BY_ID = "SELECT * FROM ROOMS WHERE ID = :id";
	private static final String SELECT_ROOM_BY_NUMBER_OF_GUESTS = "SELECT * FROM ROOMS ORDER BY NUMBER_OF_GUESTS";
	private static final String SELECT_ROOM_BY_PRICE = "SELECT * FROM ROOMS ORDER BY PRICE";
	private static final String SELECT_ROOM_BY_BREAKFAST_INCLUDED = "SELECT * FROM ROOMS ORDER BY BREAKFAST_INCLUDED";
	private static final String SELECT_ROOM_BY_PETS_ALLOWED = "SELECT * FROM ROOMS ORDER BY PETS_ALLOWED";
	private static final String SELECT_ROOM_BY_DATE_AVAILABLE_FROM = "SELECT * FROM ROOMS ORDER BY DATE_AVAILABLE_FROM";
	private static final String SELECT_ROOM_BY_ID_HOTELS = "SELECT * FROM ROOMS ORDER BY ID_HOTELS";
	private static final String SELECT_ROOM_BY_ID_AND_HOTEL_ID = "SELECT * FROM ROOMS WHERE ID = :id AND ID_HOTELS = :hotelId";
	
	public Room findById(long id) {
		try (Session session = createSessionFactory().openSession();) {
			Query<Room> query = session.createNativeQuery(SELECT_ROOM_BY_ID, Room.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (HibernateException e) {
			System.err.println("findById of Room is failed");
			System.err.println(e.getMessage());
			throw e;
		} catch (javax.persistence.NoResultException e) {
			System.out.println("Not found Room by ID");
		}
		return null;
	}
	
	public Room save(Room room) {
		return super.save(room);
	}
	
	public Room delete(long id) {
		return super.delete(id);
	}
	
	public Room update (Room room) {
		return super.update(room);
	}
	
	public Collection<Room> findRooms(Filter filter) throws Exception {
		try (Session session = createSessionFactory().openSession()) {
			Query<Room> query = null;
			switch (filter) {
				case NUMBER_OF_GUESTS:
					query = session.createNativeQuery(SELECT_ROOM_BY_NUMBER_OF_GUESTS, Room.class);
					break;
				case BREAKFAST_INCLUDED:
					query = session.createNativeQuery(SELECT_ROOM_BY_BREAKFAST_INCLUDED, Room.class);
					break;
				case DATE_AVAILABLE_FROM:
					query = session.createNativeQuery(SELECT_ROOM_BY_DATE_AVAILABLE_FROM, Room.class);
					break;
				case ID_HOTELS:
					query = session.createNativeQuery(SELECT_ROOM_BY_ID_HOTELS, Room.class);
					break;
				case PETS_ALLOWED:
					query = session.createNativeQuery(SELECT_ROOM_BY_PETS_ALLOWED, Room.class);
					break;
				case PRICE:
					query = session.createNativeQuery(SELECT_ROOM_BY_PRICE, Room.class);
					break;
				default:
					throw new Exception("bad filter-parameter");
				}
			//query.setParameter("filter", filter.toString());
			//System.out.println(filter.toString());
			return query.getResultList();
		} catch (HibernateException e) {
			System.err.println("findByFilter of Room is failed");
			System.err.println(e.getMessage());
			throw e;
		} catch (javax.persistence.NoResultException e) {
			System.out.println("Not found Room by FILTER");
		}
		return null;
	}
	
	public Room findByIdAndHotelId(long id, long hotelId) {
		try (Session session = createSessionFactory().openSession();) {
			Query<Room> query = session.createNativeQuery(SELECT_ROOM_BY_ID_AND_HOTEL_ID, Room.class);
			query.setParameter("id", id);
			query.setParameter("hotelId", hotelId);
			return query.getSingleResult();
		} catch (HibernateException e) {
			System.err.println("findByIdAndHotelId of Room is failed");
			System.err.println(e.getMessage());
			throw e;
		} catch (javax.persistence.NoResultException e) {
			System.out.println("Not found Room by ID and Hotel_ID");
		}
		return null;
	}
}
