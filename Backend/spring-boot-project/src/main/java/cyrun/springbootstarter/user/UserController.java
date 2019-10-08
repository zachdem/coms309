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
	
	@RequestMapping(method=RequestMethod.POST, value="/userlogin")
	public String validateUser(@RequestBody User user)
	{
		User found_user = userRepository.findByNetid(user.getNetid()); 
		
		if(found_user != null)
		{
			if(found_user.getPassword().equals(user.getPassword()))
			{
				return "success";
			}
		}
		
		return "fail";
	}

}
