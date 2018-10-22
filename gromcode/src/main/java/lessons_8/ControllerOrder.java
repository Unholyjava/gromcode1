package lessons_8;

public class ControllerOrder {
	
	private ServiceOrder serviceOrder;
		
	public ControllerOrder(ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	public void bookRoom(long roomId, long userId, long hotelId) {
		serviceOrder.bookRoomService(roomId, userId, hotelId);
	}
	
	public void cancelReservation(long roomId, long userId) {
		serviceOrder.cancelReservationService(roomId, userId);
	}
}
