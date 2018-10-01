package lessons_8;

import java.util.List;
import org.hibernate.HibernateException;

public class ControllerHotel {
	
	protected HotelDAO hotelDao;
		
	public ControllerHotel(HotelDAO hotelDao) {
		super();
		this.hotelDao = hotelDao;
	}

	public List<Hotel> findHotelByName (String name) throws HibernateException {
		try {
			return hotelDao.findByNameOrCity(name, "Name");
		} catch(Exception e) {
			System.out.println("not find Hotel with name = " + name + " from findHotelByName-method");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Hotel> findHotelByCity(String city) throws HibernateException {
		try {
			return hotelDao.findByNameOrCity(city, "City");
		} catch(Exception e) {
			System.out.println("not find Hotel by city = " + city + " from findHotelByCity-method");
			e.printStackTrace();
		}
		return null;
	}
}
