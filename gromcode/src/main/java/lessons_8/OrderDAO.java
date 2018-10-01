package lessons_8;

import javax.persistence.NoResultException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class OrderDAO extends CommonDAO<Order> implements DAO<Order> {
	private static final String SELECT_ORDER_BY_ROOM_AND_USER = 
				"SELECT * FROM ORDERS WHERE ROOM_ID = :room AND ID_USERS = :user";
	
	public Order findById(long id) {
		return super.findById(id);
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
		try (Session session = createSessionFactory().openSession()) {
			Query<Order> query = session.createNativeQuery(SELECT_ORDER_BY_ROOM_AND_USER, Order.class);
			query.setParameter("room", roomId);
			query.setParameter("user", userId);
			return query.getSingleResult();
		} catch (HibernateException e) {
			System.err.println("findByRoomUser of Order is failed");
			System.err.println(e.getMessage());
			throw e;
		} catch (NoResultException e) {
			System.out.println("Not found Order by Room and User");
		}
		return null;
	}
	
	public void saveOrder(long roomId, long userId, long hotelId) throws Exception {
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()){
			Order order = new Order();
			transaction = session.getTransaction();
			transaction.begin();
			Room room = new RoomDAO().findByIdAndHotelId(roomId, hotelId);
			User user = new UserDAO().findById(userId);
			if (room != null && user != null) {
				order.setRoom(room);
				order.setUserOrdered(user);
				save(order);
				transaction.commit();
			} else {
				throw new Exception("No found User or Room for Order");
			}
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Save Order is failed");
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void deleteOrder(long roomId, long userId) throws Exception {
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()){
			transaction = session.getTransaction();
			transaction.begin();
			delete(findByRoomUser(roomId, userId).getId());
			RoomDAO roomDao = new RoomDAO();
			Room room = roomDao.findById(roomId);
			room.setDateAvailableFrom(null);
			roomDao.save(room);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.err.println("Delete Order is failed");
			throw e;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
