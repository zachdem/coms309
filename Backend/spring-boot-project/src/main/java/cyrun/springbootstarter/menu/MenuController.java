package cyrun.springbootstarter.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private LocationRepository locationRepository;

	@RequestMapping(method = RequestMethod.POST, value = "/menu")
	public List<Item> getMenu(@RequestBody Location location) {

		return menuRepository.getMenuList(location.getLocation_name());

	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/locations")
	public List<Location> getMenu(){
		return locationRepository.getAllLocations();
	}
}
