package disttx.player.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import disttx.club.model.TeamDetails;
import disttx.generic.utils.DataSourceUtils;
import disttx.player.model.UserDetails;
import disttx.player.service.TestService;
import disttx.syslog.model.TestLog;

@Controller
public class TestController {
	@Autowired
	private TestService testService;
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(UserDetails ud, TestLog tl, String teamId, String teamName) {
		String str = "something wrong";
		try {
			TeamDetails td = new TeamDetails();
			td.setId(teamId);
			td.setTeamName(teamName);
			str = testService.testConn(ud, tl, td);
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
	@RequestMapping("/getUser")
	@ResponseBody
	public String getUser(String id) {
		try {
			return testService.getUserInfo(Long.parseLong(id));
		} catch(Throwable t) {
			t.printStackTrace();
			return "something wrong";
		}
	}
	@RequestMapping("/getTeam")
	@ResponseBody
	public String getTeam(String id) {
		try {
			return testService.getClubInfo(id);
		} catch(Throwable t) {
			t.printStackTrace();
			return "something wrong";
		}
	}
	@RequestMapping("/getBoth")
	@ResponseBody
	public String getBoth(String id1, String id2) {
		String result = "";
		try {
			result += testService.getLogInfo(id1);
			result += " | "+testService.getClubInfo(id2);
			return result;
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
