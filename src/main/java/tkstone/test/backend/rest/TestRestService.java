package tkstone.test.backend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.InetAddress;

@RestController
public class TestRestService {
	
	@Autowired
	private ConfigurableApplicationContext context;
	
	@RequestMapping("/invoke")
	public TestRestResponse invoke() {
		TestRestResponse response = new TestRestResponse();
		response.setMessage(buildMessage());
		return response;
	}
	
	@RequestMapping("/shutdown")
	public String shutdown() {
		this.context.close();
		return "OK";
	}
	
	private String buildMessage() {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			String ip = inetAddress.getHostAddress();
			String hostname = inetAddress.getHostName();
			return String.format("Local hostname : %s, ip : %s", hostname, ip);
		}
		catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
