package lessons_8;

public class ServiceOrder {
	
	private OrderDAO orderDao;
	
	public ServiceOrder(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}
	
	public void bookRoomService(long roomId, long userId, long hotelId) throws Exception {
		orderDao.saveOrder(roomId, userId, hotelId);
	}
	
	public void cancelReservationService(long roomId, long userId) throws Exception {
		orderDao.deleteOrder(roomId, userId);
	}
}
