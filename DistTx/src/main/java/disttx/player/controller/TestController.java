package disttx.player.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import disttx.player.model.UserDetails;
import disttx.player.service.TestService;
import disttx.syslog.model.TestLog;

@Controller
public class TestController {
	@Autowired
	private TestService testService;
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(UserDetails ud, TestLog tl) {
		String str = "something wrong";
		try {
			str = testService.testConn(ud, tl);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	@RequestMapping("/getLog")
	@ResponseBody
	public String getLog(String id) {
		try {
			return testService.getLogInfo(id);
		} catch(Exception e) {
			e.printStackTrace();
			return "something wrong";
		}
	}
//	@RequestMapping("/addUser")
//	@ResponseBody
//	public String addUser(String username, String userid, String desc) {
//		String str = "something wrong";
//		try {
//			testService.addUser(username, userid, desc);
//			str = "success";
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return str;
//	}
}
