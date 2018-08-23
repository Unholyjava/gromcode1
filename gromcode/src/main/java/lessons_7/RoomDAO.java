package lessons_7;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class RoomDAO extends CommonDAO<Room> {
	private static final String SELECT_ROOM_BY_ID = "SELECT * FROM ROOMS WHERE ID = :id";
	
	public Room findById(long id) {
		Session session = createSessionFactory().openSession();
		try  {
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
		session.close();
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
}
