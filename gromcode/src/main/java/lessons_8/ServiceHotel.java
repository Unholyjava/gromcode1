package lessons_8;

import java.util.List;

import org.hibernate.HibernateException;

public class ServiceHotel {
	
	private HotelDAO hotelDao;
	
	public ServiceHotel(HotelDAO hotelDao) {
		this.hotelDao = hotelDao;
	}

	public List<Hotel> findHotelByNameService (String name) throws HibernateException {
		try {
			return hotelDao.findByNameOrCity(name, "Name");
		} catch(Exception e) {
			System.out.println("not find Hotel with name = " + name + " from findHotelByName-method");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Hotel> findHotelByCityService(String city) throws HibernateException {
		try {
			return hotelDao.findByNameOrCity(city, "City");
		} catch(Exception e) {
			System.out.println("not find Hotel by city = " + city + " from findHotelByCity-method");
			e.printStackTrace();
		}
		return null;
	}
}
