package lessons_7;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class RoomDAO {
	private static final String SELECT_ROOM_BY_ID = "SELECT * FROM ROOMS WHERE ID = :id";
	private SessionFactory sessionFactory; 
	
	public Room save(Room room) {
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			session.save(room);
			
			transaction.commit();
			System.out.println("Save Room is done");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Save Room is failed");
			System.err.println(e.getMessage());
		}
		return room;
	}
	
	
	public Room delete(long id) {
		Transaction transaction = null;
		Room room = findById(id);
		if (room == null) {
			System.out.println("Not found Room by ID");
			return null;
		}
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			session.delete(room);
			
			transaction.commit();
			System.out.println("Delete Room is done");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Delete Room is failed");
			System.err.println(e.getMessage());
		} 
		return room;
	}
	
	public Room update(Room room) {
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			session.update(room);
			
			transaction.commit();
			System.out.println("Update is done");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Update is failed");
			System.err.println(e.getMessage());
		} 
		return room;
	}
	
	public Room findById(long id) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Room> query = session.createNativeQuery(SELECT_ROOM_BY_ID, Room.class);
			query.setParameter("id", id);
			Room room = query.getSingleResult();
			return room;
		} catch (HibernateException e) {
			System.err.println("findById of Room is failed");
			System.err.println(e.getMessage());
		} catch (javax.persistence.NoResultException e) {
			System.out.println("Not found Room by ID");
		}
		return null;
	}
	
	public SessionFactory createSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}
}
