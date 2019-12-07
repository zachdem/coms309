package cyrun.springbootstarter.user;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public String validateUserService(User user) {

		User found_user = userRepository.findByNetid(user.getNetid());

		if (found_user != null) {
			if (found_user.getPassword().equals(user.getPassword())) {
				return "success";
			}
		}

		return "fail";
	}

	public String signUpUserService(User user) {
		// Verify the user doesn't exist, if it does exist already return fail
		if (verifyUserExists(user)) {
			return "user_already_exists";
		}

		userRepository.insertNewUser(user.getFirst_name(), user.getLast_name(), user.getUsername(), user.getPassword(),
				user.getIsu_id(), user.getRouting_number(), user.getAccount_number(), user.getNetid());

		// Verify the record was posted, if posted return success
		if (verifyUserExists(user)) {
			return "signup_success";
		}

		return "fail";
	}

	public boolean verifyUserExists(User user) {
		User found_user = userRepository.findByNetid(user.getNetid());
		if (found_user != null) {
			return true;
		} else {
			return false;
		}

	}

	public String getUserBalance(String netid) {
		User user = userRepository.findByNetid(netid);
		return String.valueOf(user.getBalance());
	}
	
	public void deductUserBalance(Double amount, String netid) {
		userRepository.deductBalance(amount, netid);
	}

}
