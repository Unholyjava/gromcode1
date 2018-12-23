package lessons_8;

import java.util.List;

public class ServiceRoom {
	
	private RoomDAO roomDao;
	
	public ServiceRoom(RoomDAO roomDao) {
		this.roomDao = roomDao;
	}
	
	public List<Room> findRoomsService(Filter filter) throws Exception {
		return roomDao.findRooms(filter);
	}
}
