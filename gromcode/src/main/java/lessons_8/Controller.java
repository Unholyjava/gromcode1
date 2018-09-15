package lessons_8;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;

public class Controller {
	
	protected HotelDAO hotelDao;
	protected OrderDAO orderDao;
	protected RoomDAO roomDao;
	protected UserDAO userDao;
	
	public Controller(HotelDAO hotelDao, OrderDAO orderDao, RoomDAO roomDao, UserDAO userDao) {
		super();
		this.hotelDao = hotelDao;
		this.orderDao = orderDao;
		this.roomDao = roomDao;
		this.userDao = userDao;
	}

	public Hotel findHotelByName (String name) throws HibernateException {
		try {
			return hotelDao.findByName(name);
		} catch(Exception e) {
			System.out.println("not find Hotel with name = " + name + " from findHotelByName-method");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Hotel> findHotelByCity(String city) throws HibernateException {
		try {
			return hotelDao.findByCity(city);
		} catch(Exception e) {
			System.out.println("not find Hotel by city = " + city + " from findHotelByCity-method");
			e.printStackTrace();
		}
		return null;
	}

	public Collection<Room> findRooms(Filter filter) throws HibernateException {
		try {
			return roomDao.findRooms(filter);
		} catch(Exception e) {
			System.out.println("not find Rooms by filter = " + filter + " from findRooms-method");
			e.printStackTrace();
		}
		return null;
	}
	
	public void bookRoom(long roomId, long userId, long hotelId) throws HibernateException {
		try {
			Order order = new Order();
			Room room = roomDao.findByIdAndHotelId(roomId, hotelId);
			User user = userDao.findById(userId);
			if (room != null && user != null) {
				order.setRoom(room);
				order.setUserOrdered(user);
				orderDao.save(order);
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			System.out.println("not create Order with room-id = " + roomId 
				+ ", user-id = " + userId
				+ ", hotel-id = " + hotelId + " from bookRoom-method");
			e.printStackTrace();
		}
	}
	
	public void cancelReservation(long roomId, long userId) throws HibernateException {
		try {
			orderDao.delete(orderDao.findByRoomUser(roomId, userId).getId());
		} catch(Exception e) {
			System.out.println("not cancel Order with room-id = " + roomId
				+ ", user-id = " + userId + " from cancelReservation-method");
			e.printStackTrace();
		}
	}
	
	public User registerUser(User user) throws HibernateException {
		try {
			User userRegistered = userDao.findByNamePassword(user);
			if (userRegistered == null) {
				throw new Exception();
			}
			return userRegistered;
		} catch (Exception e){
			System.out.println("not found register User with name = " + user.getUserName()
				+ " from registerUser-method");
			e.printStackTrace();
		}
		return null;
	}
	
	public void login(String userName, String password) throws HibernateException {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		try {
			if (userDao.findByNamePassword(user) == null) {
				userDao.save(user);
			} else {
				System.out.println("Пользователь с таким именем и паролем зарегистрирован");
			}
		} catch (Exception e) {
			System.out.println("not login User with name = " + userName
				+ ", password = " + password + " from login-method");
			e.printStackTrace();
		}
	}
	
	public void logout() {
		
		
	}
}
