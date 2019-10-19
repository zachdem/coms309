package cyrun.springbootstarter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepository userRepository;

	public String validateUserService(User user)
	{
		
		User found_user = userRepository.findByNetid(user.getNetid());

		if (found_user != null) {
			if (found_user.getPassword().equals(user.getPassword())) {
				return "success";
			}
		}

		return "fail";
	}
	
}
