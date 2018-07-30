package lessons_7;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Demo {

	public static void main(String[] args) {
		Hotel hotel1 = new Hotel();
		hotel1.setId(1);
		hotel1.setName("Dnipro");
		hotel1.setCountry("Ukraine");
		hotel1.setCity("Kyiv");
		hotel1.setStreet("Hreshatik, 1/2");
		
		Hotel hotel2 = new Hotel();
		hotel2.setId(1);
		hotel2.setName("Metropol");
		hotel2.setCountry("Russia");
		hotel2.setCity("Moskva");
		hotel2.setStreet("Theater journey, 2");
		
		Room room1 = new Room();
		room1.setId(10);
		room1.setNumberOfGuests(4);
		room1.setPrice(2000);
		room1.setBreakfastIncluded(1);
		room1.setPetsAllowed(1);
		GregorianCalendar dataRoom1 = new GregorianCalendar(2018, Calendar.AUGUST, 1);
		room1.setDateAvailableFrom(dataRoom1.getTime());
		room1.setHotel(hotel1);
		
		Room room2 = new Room();
		room2.setId(10);
		room2.setNumberOfGuests(4);
		room2.setPrice(3000);
		room2.setBreakfastIncluded(1);
		room2.setPetsAllowed(1);
		GregorianCalendar dataRoom2 = new GregorianCalendar(2018, Calendar.SEPTEMBER, 12);
		room2.setDateAvailableFrom(dataRoom2.getTime());
		room2.setHotel(hotel2);
		
		HotelDAO hotelDao = new HotelDAO();
		RoomDAO roomDao = new RoomDAO();
		System.out.println("SAVE");
		System.out.println(hotelDao.save(hotel1));
		System.out.println(roomDao.save(room1));
		
		System.out.println("UPDATE");
		System.out.println(hotelDao.update(hotel2));
		System.out.println(roomDao.update(room2));
		
		System.out.println("FIND");
		System.out.println(hotelDao.findById(58));
		System.out.println(hotelDao.findById(1));
		System.out.println(roomDao.findById(10));
				
		System.out.println("DELETE");
		Room room = roomDao.delete(10);
		if (room == null) {
			System.out.println("null");
		} else {
			System.out.println(room.toString());
		}
		Hotel hotel = hotelDao.delete(1); 
		if (hotel == null) {
			System.out.println("null");
		} else {
			System.out.println(hotel.toString());
		}
		
	}
}
