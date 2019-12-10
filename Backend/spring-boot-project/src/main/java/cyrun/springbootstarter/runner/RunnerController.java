package cyrun.springbootstarter.runner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cyrun.springbootstarter.user.User;

@RestController
public class RunnerController {
	
	@Autowired
	private RunnerService runnerService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/runnerlogin")
	public String validateRunner(@RequestBody Runner runner) {
		
		return runnerService.validateRunnerService(runner);
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/runner_signup")
	public String signUpRunner(@RequestBody Runner runner) {
		
		return runnerService.signUpRunnerService(runner);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/runner/{netid}")
	public List<Runner> getInfo(@PathVariable String netid) {
		return runnerService.getRunnerInfoService(netid);

	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/runner/{netid}")
	public String updateInfo(@RequestBody Runner runner) {
		return runnerService.updateSettings(runner);
	}
}
