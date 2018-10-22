package lessons_8;

import java.util.List;

import org.hibernate.HibernateException;

public class ServiceRoom {
	
	private RoomDAO roomDao;
	
	public ServiceRoom(RoomDAO roomDao) {
		this.roomDao = roomDao;
	}
	
	public List<Room> findRoomsService(Filter filter) throws HibernateException {
		try {
			return roomDao.findRooms(filter);
		} catch(Exception e) {
			System.out.println("not find Rooms by filter = " + filter + " from findRooms-method");
			e.printStackTrace();
		}
		return null;
	}
}
