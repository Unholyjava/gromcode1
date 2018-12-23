package lessons_8;

import java.util.List;

public class ControllerHotel {
	
	private ServiceHotel serviceHotel;
		
	public ControllerHotel(ServiceHotel serviceHotel) {
		this.serviceHotel = serviceHotel;
	}

	public List<Hotel> findHotelByName (String name) throws Exception {
		return serviceHotel.findHotelByNameService(name); 
	}
	
	public List<Hotel> findHotelByCity(String city) throws Exception {
		return serviceHotel.findHotelByCityService(city);
	}
}
