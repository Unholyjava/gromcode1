package lessons_8;

import java.util.List;
import org.hibernate.HibernateException;

public class ControllerRoom {
	
	protected RoomDAO roomDao;
		
	public ControllerRoom(RoomDAO roomDao) {
		super();
		this.roomDao = roomDao;
	}

	public List<Room> findRooms(Filter filter) throws HibernateException {
		try {
			return roomDao.findRooms(filter);
		} catch(Exception e) {
			System.out.println("not find Rooms by filter = " + filter + " from findRooms-method");
			e.printStackTrace();
		}
		return null;
	}
}
