package lessons_8;

import java.util.List;

public class ControllerRoom {
	
	private ServiceRoom serviceRoom;
		
	public ControllerRoom(ServiceRoom serviceRoom) {
		this.serviceRoom = serviceRoom;
	}

	public List<Room> findRooms(Filter filter) throws Exception {
		return serviceRoom.findRoomsService(filter);
	}
}
