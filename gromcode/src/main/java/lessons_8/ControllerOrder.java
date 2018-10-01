package lessons_8;

import org.hibernate.HibernateException;

public class ControllerOrder {
	
	protected OrderDAO orderDao;
		
	public ControllerOrder(OrderDAO orderDao) {
		super();
		this.orderDao = orderDao;
	}

	public void bookRoom(long roomId, long userId, long hotelId) throws HibernateException {
		try {
			orderDao.saveOrder(roomId, userId, hotelId);
		} catch(Exception e) {
			System.out.println("not create Order with room-id = " + roomId 
				+ ", user-id = " + userId
				+ ", hotel-id = " + hotelId + " from bookRoom-method");
			e.printStackTrace();
		}
	}
	
	public void cancelReservation(long roomId, long userId) throws HibernateException {
		try {
			orderDao.deleteOrder(roomId, userId);
		} catch(Exception e) {
			System.out.println("not cancel Order with room-id = " + roomId
				+ ", user-id = " + userId + " from cancelReservation-method");
			e.printStackTrace();
		}
	}
}
