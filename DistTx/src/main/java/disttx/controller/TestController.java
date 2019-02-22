package disttx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import disttx.service.TestService;

@Controller
public class TestController {
	@Autowired
	private TestService testService;
	
	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		String str = "something wrong";
		try {
			str = testService.testconn();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	@RequestMapping("/addUser")
	@ResponseBody
	public String addUser(String username, String userid, String desc) {
		String str = "something wrong";
		try {
			testService.addUser(username, userid, desc);
			str = "success";
		} catch(Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
