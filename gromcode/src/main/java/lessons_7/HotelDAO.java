package lessons_7;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HotelDAO {
	private static final String SELECT_HOTEL_BY_ID = "SELECT * FROM HOTELS WHERE ID = :id";
	private SessionFactory sessionFactory; 
	
	public Hotel save(Hotel hotel) {
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			session.save(hotel);
			
			transaction.commit();
			System.out.println("Save Hotel is done");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Save Hotel is failed");
			System.err.println(e.getMessage());
		}
		return hotel;
	}
	
	
	public Hotel delete(long id) {
		Transaction transaction = null;
		Hotel hotel = findById(id);
		if (hotel == null) {
			System.out.println("Not found Hotel by ID");
			return null;
		}
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			session.delete(hotel);
			
			transaction.commit();
			System.out.println("Delete Hotel is done");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Delete Hotel is failed");
			System.err.println(e.getMessage());
		} 
		return hotel;
	}
	
	public Hotel update(Hotel hotel) {
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()) {
			transaction = session.getTransaction();
			transaction.begin();
			
			session.update(hotel);
			
			transaction.commit();
			System.out.println("Update Hotel is done");
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Update Hotel is failed");
			System.err.println(e.getMessage());
		} 
		return hotel;
	}
	
	public Hotel findById(long id) {
		try (Session session = createSessionFactory().openSession()) {
			Query<Hotel> query = session.createNativeQuery(SELECT_HOTEL_BY_ID, Hotel.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (HibernateException e) {
			System.err.println("findById of Hotel is failed");
			System.err.println(e.getMessage());
		} catch (javax.persistence.NoResultException e) {
			System.out.println("Not found Hotel by ID");
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
