package cyrun.springbootstarter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableScheduling
public class AppConfiguration {
	
	@Bean
	public ServerEndpointExporter serverEndpointExporter(){
	return new ServerEndpointExporter(); 
	}
}
