package cyrun.springbootstarter.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}