package lessons_8;

public class Demo {
	public static void main(String[] args) {
		
		HotelDAO hotelDao = new HotelDAO(Hotel.class);
		OrderDAO orderDao = new OrderDAO(Order.class);
		RoomDAO roomDao = new RoomDAO(Room.class);
		UserDAO userDao = new UserDAO(User.class);
		ServiceUser serviceUser = new ServiceUser(userDao);
		ServiceOrder serviceOrder = new ServiceOrder(orderDao);
		ServiceHotel serviceHotel = new ServiceHotel(hotelDao);
		ServiceRoom serviceRoom = new ServiceRoom(roomDao);
		ControllerHotel controllerHotel = new ControllerHotel(serviceHotel);
		ControllerRoom controllerRoom = new ControllerRoom(serviceRoom);
		ControllerOrder controllerOrder = new ControllerOrder(serviceOrder);
		ControllerUser controllerUser = new ControllerUser(serviceUser);
		
		setHotelAndRoom(hotelDao, roomDao);
						
		try {
			System.out.println(controllerRoom.findRooms(Filter.ID_HOTELS));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		System.out.println("Список комнат по отелям\n");
		
		try {
			System.out.println(controllerRoom.findRooms(Filter.PRICE));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		System.out.println("Список комнат по цене\n");
		
		try {
			System.out.println(controllerRoom.findRooms(Filter.NUMBER_OF_GUESTS));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		System.out.println("Список комнат по гостям\n");
		
		String name = "Vasya";
		String name1 = "Vasya1";
		String password = "12345";
		
		User user = new User();
		user.setUserName(name);
		user.setPassword(password);
		try {
			System.out.println(controllerUser.registerUser(user));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("Регистрация нового пользователя\n");
		
		try {
			System.out.println(controllerUser.registerUser(user));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("Попытка регистрации существующего пользователя\n");
		
		try {
			controllerUser.login(name, password);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println(serviceUser.getSessionUser().getUserSession());
		System.out.println("Логгирование пользователя - есть\n");
		
		controllerUser.logout();
		System.out.println(serviceUser.getSessionUser().getUserSession());
		System.out.println("Логгирование пользователя - вышел\n");
		
		try {
			controllerUser.login(name1, password);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("Логгирование пользователя - он не зарегистрирован\n");
		
		
		try {
			System.out.println(controllerHotel.findHotelByCity("Dnipro"));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		System.out.println("Поиск отеля по городу\n");
		
		try {
			System.out.println(controllerHotel.findHotelByCity("Kiev"));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		System.out.println("Поиск отеля по городу (его нет в БД)\n");
		
		try {
			System.out.println(controllerHotel.findHotelByName("Dnepropetrovsk"));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		System.out.println("Поиск отеля по имени\n");
		
		try {
			System.out.println(controllerHotel.findHotelByCity("Hutor"));
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		System.out.println("Поиск отеля по имени (его нет в БД)\n");
		
		user.setUserName(name);
		try {
			controllerOrder.bookRoom(1, userDao.findByNamePassword(user.getUserName(), user.getPassword()).getId(), 1);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("Оформление заказа\n");
		
		try {
			controllerOrder.bookRoom(10, userDao.findByNamePassword(user.getUserName(), user.getPassword()).getId(), 2);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("Оформление заказа (ошибочные данные)\n");
		
		try {
			controllerOrder.cancelReservation(2, userDao.findByNamePassword(user.getUserName(), user.getPassword()).getId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("Отмена заказа (ошибочные данные)\n");
		
		try {
			controllerOrder.cancelReservation(1, userDao.findByNamePassword(user.getUserName(), user.getPassword()).getId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("Отмена заказа\n");
	}

	private static void setHotelAndRoom(HotelDAO hotelDao, RoomDAO roomDao) {
		Hotel hotel1 = new Hotel();
		Hotel hotel2 = new Hotel();
		Hotel hotel3 = new Hotel();
		
		hotel1.setId(1);
		hotel1.setName("Dnepropetrovsk");
		hotel1.setCountry("Ukraine");
		hotel1.setCity("Dnipro");
		hotel2.setId(2);
		hotel2.setName("Abry");
		hotel2.setCountry("Ukraine");
		hotel2.setCity("Dnipro");
		hotel3.setId(3);
		hotel3.setName("NewPort");
		hotel3.setCountry("Ukraine");
		hotel3.setCity("Dnipro");
		
		Room room11 = new Room();
		Room room12 = new Room();
		Room room13 = new Room();
		Room room21 = new Room();
		Room room22 = new Room();
		Room room31 = new Room();
		
		room11.setId(1);
		room11.setHotel(hotel1);
		room11.setNumberOfGuests(4);
		room11.setPrice(800);
		room12.setId(2);
		room12.setHotel(hotel1);
		room12.setNumberOfGuests(3);
		room12.setPrice(1000);
		room13.setId(3);
		room13.setHotel(hotel1);
		room13.setNumberOfGuests(2);
		room13.setPrice(600);
		room21.setId(4);
		room21.setHotel(hotel2);
		room21.setNumberOfGuests(4);
		room21.setPrice(900);
		room22.setId(5);
		room22.setHotel(hotel2);
		room22.setNumberOfGuests(2);
		room22.setPrice(700);
		room31.setId(6);
		room31.setHotel(hotel3);
		room31.setNumberOfGuests(1);
		room31.setPrice(800);
		
		try {
			hotelDao.save(hotel1);
			hotelDao.save(hotel2);
			hotelDao.save(hotel3);
			roomDao.save(room11);
			roomDao.save(room12);
			roomDao.save(room13);
			roomDao.save(room21);
			roomDao.save(room22);
			roomDao.save(room31);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
