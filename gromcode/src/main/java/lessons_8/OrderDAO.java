package lessons_8;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class OrderDAO extends CommonDAO<Order> implements DAO<Order> {
	public OrderDAO(Class<Order> classCurrent) {
		super(classCurrent);
	}

	private static final String SELECT_ORDER_BY_ROOM_AND_USER = 
				"SELECT * FROM ORDERS WHERE ROOM_ID = :room AND ID_USERS = :user";
	
	public Order findById(long id) throws Exception {
		return super.findById(id);
	}
	
	public Order save(Order order) throws Exception {
		return super.save(order);
	}
	
	public Order delete(long id) throws Exception {
		return super.delete(id);
	}
	
	public Order update (Order order) throws Exception {
		return super.update(order);
	}
	
	public Order findByRoomUser(long roomId, long userId) throws Exception {
		try (Session session = createSessionFactory().openSession()) {
			Query<Order> query = session.createNativeQuery(SELECT_ORDER_BY_ROOM_AND_USER, Order.class);
			query.setParameter("room", roomId);
			query.setParameter("user", userId);
			return query.getSingleResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new Exception("findByRoomUser of Order is failed");
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void saveOrder(long roomId, long userId, long hotelId) throws Exception {
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()){
			Order order = new Order();
			transaction = session.getTransaction();
			transaction.begin();
			Room room = new RoomDAO(Room.class).findByIdAndHotelId(roomId, hotelId);
			User user = new UserDAO(User.class).findById(userId);
			if (room != null && user != null) {
				order.setRoom(room);
				order.setUserOrdered(user);
				save(order);
				transaction.commit();
			} else {
				throw new Exception("No found User or Room for Order\n"
					+ "not create Order with room-id = " + roomId 
					+ ", user-id = " + userId
					+ ", hotel-id = " + hotelId);
			}
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new Exception ("Save Order is failed");
		}
	}
	
	public void deleteOrder(long roomId, long userId) throws Exception {
		Transaction transaction = null;
		try (Session session = createSessionFactory().openSession()){
			transaction = session.getTransaction();
			transaction.begin();
			if (findByRoomUser(roomId, userId) == null) {
				throw new Exception ("Not cancel Order with room-id = " + roomId
						+ ", user-id = " + userId);
			}
			delete(findByRoomUser(roomId, userId).getId());
			RoomDAO roomDao = new RoomDAO(Room.class);
			Room room = roomDao.findById(roomId);
			room.setDateAvailableFrom(null);
			roomDao.update(room);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new Exception ("Delete Order is failed");
		}
	}
}
