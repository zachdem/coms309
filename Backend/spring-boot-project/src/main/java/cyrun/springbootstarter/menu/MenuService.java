package cyrun.springbootstarter.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	public List<Item> getMenuListService(String locationName)
	{
		return menuRepository.getMenuList(locationName);
	}
	
	
	public List<Location> getLocationsService()
	{
		return locationRepository.getAllLocations();
	}
	
	
}
