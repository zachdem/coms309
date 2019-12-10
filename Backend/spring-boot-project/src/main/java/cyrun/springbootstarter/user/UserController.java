package cyrun.springbootstarter.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cyrun.springbootstarter.menu.Item;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST, value = "/userlogin")
	public String validateUser(@RequestBody User user) {

		return userService.validateUserService(user);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user_signup")
	public String signUpUser(@RequestBody User user) {

		return userService.signUpUserService(user);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/balance/{netid}")
	public String getUserBalance(@PathVariable String netid) {
		return userService.getUserBalance(netid);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/{netid}")
	public List<User> getInfo(@PathVariable String netid) {
		return userService.getUserInfoService(netid);

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/settings/{netid}")
	public String updateSettings(@PathVariable String netid) {
		return userService.updateSettings(netid);
	}
}
