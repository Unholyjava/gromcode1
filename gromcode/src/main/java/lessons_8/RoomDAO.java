package lessons_8;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class RoomDAO extends CommonDAO<Room> implements DAO<Room> {
	public RoomDAO(Class<Room> classCurrent) {
		super(classCurrent);
	}

	private static final String SELECT_ROOM_BY_NUMBER_OF_GUESTS = "SELECT * FROM ROOMS ORDER BY NUMBER_OF_GUESTS";
	private static final String SELECT_ROOM_BY_PRICE = "SELECT * FROM ROOMS ORDER BY PRICE";
	private static final String SELECT_ROOM_BY_BREAKFAST_INCLUDED = "SELECT * FROM ROOMS ORDER BY BREAKFAST_INCLUDED";
	private static final String SELECT_ROOM_BY_PETS_ALLOWED = "SELECT * FROM ROOMS ORDER BY PETS_ALLOWED";
	private static final String SELECT_ROOM_BY_DATE_AVAILABLE_FROM = "SELECT * FROM ROOMS ORDER BY DATE_AVAILABLE_FROM";
	private static final String SELECT_ROOM_BY_ID_HOTELS = "SELECT * FROM ROOMS ORDER BY ID_HOTELS";
	private static final String SELECT_ROOM_BY_ID_AND_HOTEL_ID = "SELECT * FROM ROOMS WHERE ID = :id AND ID_HOTELS = :hotelId";
	
	public Room findById(long id) throws Exception {
		return super.findById(id);
	}
	
	public Room save(Room room) throws Exception {
		return super.save(room);
	}
	
	public Room delete(long id) throws Exception {
		return super.delete(id);
	}
	
	public Room update (Room room) throws Exception {
		return super.update(room);
	}
	
	public List<Room> findRooms(Filter filter) throws Exception {
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
			return query.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new Exception("findByFilter of Room is failed");
		}
	}
	
	public Room findByIdAndHotelId(long id, long hotelId) throws Exception {
		try (Session session = createSessionFactory().openSession();) {
			Query<Room> query = session.createNativeQuery(SELECT_ROOM_BY_ID_AND_HOTEL_ID, Room.class);
			query.setParameter("id", id);
			query.setParameter("hotelId", hotelId);
			return query.getSingleResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new Exception ("findByIdAndHotelId of Room is failed");
		} catch (NoResultException e) {
			return null;
		}
	}
}
	
