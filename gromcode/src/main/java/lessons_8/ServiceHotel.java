package lessons_8;

import java.util.List;

public class ServiceHotel {
	
	private HotelDAO hotelDao;
	
	public ServiceHotel(HotelDAO hotelDao) {
		this.hotelDao = hotelDao;
	}

	public List<Hotel> findHotelByNameService (String name) throws Exception {
		return hotelDao.findByNameOrCity(name, "Name");
	}
	
	public List<Hotel> findHotelByCityService(String city) throws Exception {
		return hotelDao.findByNameOrCity(city, "City");
	}
}
