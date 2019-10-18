package cyrun.springbootstarter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(method = RequestMethod.POST, value = "/userlogin")
	public String validateUser(@RequestBody User user) {
		User found_user = userRepository.findByNetid(user.getNetid());

		if (found_user != null) {
			if (found_user.getPassword().equals(user.getPassword())) {
				return "success";
			}
		}

		return "fail";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user_signup")
	public String signUpUser(@RequestBody User user) {
		
		//Verify the user doesn't exists, if it does exist already return fail
		User found_user = userRepository.findByNetid(user.getNetid());
		if (found_user != null) {
			return "user_already_exists";
		}
		
		
		userRepository.insertNewUser(user.getFirst_name(), user.getLast_name(), user.getUsername(),
				user.getPassword(), user.getIsu_id(), user.getRouting_number(), user.getAccount_number(),
				user.getNetid());
		
		//Verify the record was posted, if posted return success
		found_user = userRepository.findByNetid(user.getNetid());
		if (found_user != null) {
			return "signup_success";
		}

		return "fail";
	}

}
