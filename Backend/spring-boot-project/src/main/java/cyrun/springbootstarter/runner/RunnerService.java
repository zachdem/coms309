package cyrun.springbootstarter.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyrun.springbootstarter.runner.Runner;
import cyrun.springbootstarter.runner.RunnerRepository;
import cyrun.springbootstarter.user.User;

@Service
public class RunnerService {
	
	@Autowired
	private RunnerRepository runnerRepository;

	public String validateRunnerService(Runner runner) {

		Runner found_runner = runnerRepository.findByNetid(runner.getNetid());

		if (found_runner != null) {
			if (found_runner.getPassword().equals(runner.getPassword())) {
				return "success";
			}
		}

		return "fail";
	}

	public String signUpRunnerService(Runner runner) {
		// Verify the user doesn't exist, if it does exist already return fail
		if(verifyRunnerExists(runner))
		{
			return "runner_already_exists";
		}

		runnerRepository.insertNewRunner(runner.getFirst_name(), runner.getLast_name(), runner.getUsername(), runner.getPassword(),
				runner.getIsu_id(), runner.getRouting_number(), runner.getAccount_number(), runner.getNetid());

		// Verify the record was posted, if posted return success
		if (verifyRunnerExists(runner)) {
			return "signup_success";
		}

		return "fail";
	}
	
	public boolean verifyRunnerExists(Runner runner)
	{
		Runner found_runner = runnerRepository.findByNetid(runner.getNetid());
		if (found_runner != null) {
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public List<Runner> getRunnerInfoService(String netid)
	{
		return runnerRepository.getRunnerList(netid);
	}

}
