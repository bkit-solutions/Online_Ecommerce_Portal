package Com.smarttrends.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Amazon3")
public class UserRegistration {

	@GetMapping("/reg3")
	public String createUser()
	{
		return "api is working sucessfully";
	}
}
