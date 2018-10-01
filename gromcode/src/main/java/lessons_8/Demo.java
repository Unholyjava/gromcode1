package lessons_8;

public class Demo {
	public static void main(String[] args) {
		
		HotelDAO hotelDao = new HotelDAO();
		OrderDAO orderDao = new OrderDAO();
		RoomDAO roomDao = new RoomDAO();
		UserDAO userDao = new UserDAO();
		ControllerHotel controllerHotel = new ControllerHotel(hotelDao);
		ControllerRoom controllerRoom = new ControllerRoom(roomDao);
		ControllerOrder controllerOrder = new ControllerOrder(orderDao);
		ControllerUser controllerUser = new ControllerUser(userDao);
		
		setHotelAndRoom(controllerHotel, controllerRoom);
		
		System.out.println(controllerRoom.findRooms(Filter.ID_HOTELS));
		System.out.println("Список комнат по отелям\n");
		
		System.out.println(controllerRoom.findRooms(Filter.PRICE));
		System.out.println("Список комнат по цене\n");
		
		System.out.println(controllerRoom.findRooms(Filter.NUMBER_OF_GUESTS));
		System.out.println("Список комнат по гостям\n");
		
		String name = "Vasya";
		String name1 = "Vasya1";
		String password = "12345";
		controllerUser.login(name, password);
		System.out.println("Попытка регистрации\n");
		
		User user = new User();
		user.setUserName(name);
		user.setPassword(password);
		System.out.println(controllerUser.registerUser(user));
		System.out.println("Поиск зарегистрированного пользователя\n");
		
		controllerUser.login(name, password);
		System.out.println("Попытка повторной регистрации\n");
		
		user.setUserName(name1);
		user.setPassword(password);
		System.out.println(controllerUser.registerUser(user));
		System.out.println("Поиск зарегистрированного (его нет в БД) пользователя\n");
		
		System.out.println(controllerHotel.findHotelByCity("Dnipro"));
		System.out.println("Поиск отеля по городу\n");
		
		System.out.println(controllerHotel.findHotelByCity("Kiev"));
		System.out.println("Поиск отеля по городу (его нет в БД)\n");
		
		System.out.println(controllerHotel.findHotelByName("Dnepropetrovsk"));
		System.out.println("Поиск отеля по имени\n");
		
		System.out.println(controllerHotel.findHotelByCity("Hutor"));
		System.out.println("Поиск отеля по имени (его нет в БД)\n");
		
		user.setUserName(name);
		controllerOrder.bookRoom(1, userDao.findByNamePassword(user).getId(), 1);
		System.out.println("Оформление заказа\n");
		
		controllerOrder.bookRoom(10, userDao.findByNamePassword(user).getId(), 2);
		System.out.println("Оформление заказа (ошибочные данные)\n");
		
		controllerOrder.cancelReservation(2, userDao.findByNamePassword(user).getId());
		System.out.println("Отмена заказа (ошибочные данные)\n");
		
		controllerOrder.cancelReservation(1, userDao.findByNamePassword(user).getId());
		System.out.println("Отмена заказа\n");
	}

	private static void setHotelAndRoom(ControllerHotel controllerHotel, ControllerRoom controllerRoom) {
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
		
		controllerHotel.hotelDao.save(hotel1);
		controllerHotel.hotelDao.save(hotel2);
		controllerHotel.hotelDao.save(hotel3);
		controllerRoom.roomDao.save(room11);
		controllerRoom.roomDao.save(room12);
		controllerRoom.roomDao.save(room13);
		controllerRoom.roomDao.save(room21);
		controllerRoom.roomDao.save(room22);
		controllerRoom.roomDao.save(room31);
	}
}
