package cyrun.springbootstarter.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{location_name}")
	public List<Item> getMenu(@PathVariable String location_name) {
		return menuService.getMenuListService(location_name);

	}
	
	@RequestMapping(method=RequestMethod.GET, value="/locations")
	public List<Location> getLocations(){
		return menuService.getLocationsService();
	}
}
