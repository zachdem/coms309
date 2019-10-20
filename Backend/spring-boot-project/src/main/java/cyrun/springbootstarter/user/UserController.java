package cyrun.springbootstarter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
