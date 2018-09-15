package lessons_8;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class OrderDAO extends CommonDAO<Order> implements DAO<Order> {
	private static final String SELECT_ORDER_BY_ID = "SELECT * FROM ORDERS WHERE ID = :id";
	private static final String SELECT_ORDER_BY_ROOM_AND_USER = 
				"SELECT * FROM ORDERS WHERE ROOM_ID = :room AND ID_USERS = :user";
	
	public Order findById(long id) {
		try (Session session = createSessionFactory().openSession();) {
			Query<Order> query = session.createNativeQuery(SELECT_ORDER_BY_ID, Order.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (HibernateException e) {
			System.err.println("findById of Order is failed");
			System.err.println(e.getMessage());
			throw e;
		} catch (javax.persistence.NoResultException e) {
			System.out.println("Not found Order by ID");
		}
		return null;
	}
	
	public Order save(Order order) {
		return super.save(order);
	}
	
	public Order delete(long id) {
		return super.delete(id);
	}
	
	public Order update (Order order) {
		return super.update(order);
	}
	
	public Order findByRoomUser(long roomId, long userId) {
		try (Session session = createSessionFactory().openSession();) {
			Query<Order> query = session.createNativeQuery(SELECT_ORDER_BY_ROOM_AND_USER, Order.class);
			query.setParameter("room", roomId);
			query.setParameter("user", userId);
			return query.getSingleResult();
		} catch (HibernateException e) {
			System.err.println("findByRoomUser of Order is failed");
			System.err.println(e.getMessage());
			throw e;
		} catch (javax.persistence.NoResultException e) {
			System.out.println("Not found Order by Room and User");
		}
		return null;
	}
}
