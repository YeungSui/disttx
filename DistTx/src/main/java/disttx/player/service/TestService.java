package disttx.player.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import disttx.club.model.TeamDetails;
import disttx.club.repository.TeamDetailsDao;
import disttx.generic.utils.AppContextUtils;
import disttx.generic.utils.DataSourceUtils;
import disttx.player.model.UserDetails;
import disttx.player.repository.UserDetailsDao;
import disttx.syslog.model.TestLog;
import disttx.syslog.repository.TestLogDao;

@Service
@Transactional("transactionManager")
public class TestService {
	@Autowired
	private UserDetailsDao userDetailsDao;
	@Autowired
	private TestLogDao testLogDao;
	@Autowired
	private TeamDetailsDao clubDao;
	
	public String testConn(UserDetails ud, TestLog tl, TeamDetails td) {
		String result = "success";
		userDetailsDao.save(ud);
		DataSourceUtils.useDataSource("kwanpaang",true);
		tl.setLogTime(new Timestamp(new Date().getTime()));
		testLogDao.save(tl);
		DataSourceUtils.useDataSource("barhbe");
		clubDao.save(td);
		return result;
	}
	public void addTestLog(TestLog tl) {
		DataSourceUtils.useDataSource("kwanpaang");
		tl.setLogTime(new Timestamp(new Date().getTime()));
		testLogDao.save(tl);
	}
	public String getLogInfo(String id) {
		String result = "nothing";
		DataSourceUtils.useDataSource("kwanpaang",true);
		TestLog tl = testLogDao.findById(id);
		if(tl != null) {
			result = "id:"+tl.getId()+" time:"+tl.getLogTime()+" threadNo:"+tl.getThreadInfo();
		}
		return result;
	}
	public void someError() throws SQLException {
		throw new SQLException("INCIDENT EXCEPTION","0x0000",2000);
	}
//	public String testconn() throws SQLException {
//		String str = "nothing";
//		Connection conn2 = getDataSource2().getConnection();
//		PreparedStatement stmt2 = conn2.prepareStatement("insert into test_log(id, logtime, threadinfo) values(?,?,?)");
//		stmt2.setString(1, UUID.randomUUID().toString().replaceAll("-", ""));
//		stmt2.setTimestamp(2, new Timestamp(new Date().getTime()));
//		stmt2.setString(3, ""+Thread.currentThread().getId());
//		stmt2.executeUpdate();
//		stmt2.close();
//		conn2.close();
//		Connection conn1 = getDataSource1().getConnection();
//		Statement stmt1 = conn1.createStatement();
//		ResultSet rs = stmt1.executeQuery("select * from user_details where user_id=200");
//		if(rs.next()) {
//			str = rs.getString(2);
//		}
//		rs.close();
//		stmt1.close();
//		conn1.close();
//		return str;
//	}
//	public void addUser(String username, String userid, String desc) throws SQLException {
//		Connection conn = getDataSource1().getConnection();
//		Statement stmt1 = conn.createStatement();
//		stmt1.executeUpdate("insert into user_details(user_id, user_name, description) values ("+userid+",'"+username+"','"+desc+"')");
//		conn.close();
//	}
//	
//	private DataSource getDataSource1() {
//		ApplicationContext ac = AppContextUtils.getApplicationContext();
//		Object ds = ac.getBean("primaryDs");
//		if(ds instanceof DataSource) {
//			return (DataSource)ds;
//		}
//		return null;
//	}
//	
//	private DataSource getDataSource2() {
//		ApplicationContext ac = AppContextUtils.getApplicationContext();
//		if(ac != null) {
//			Object ds = ac.getBean("secondaryDs");
//			if(ds instanceof DataSource) {
//				return (DataSource) ds;
//			}
//		}
//		return null;
//	}
	public String getUserInfo(Long id) {
		UserDetails ud = userDetailsDao.findById(id);
		if(ud != null) {
			return "User with id: "+ud.getUserId()+" named "+ud.getUserName()+" has been found.";
		} else {
			return "Cannot find user with id: "+id;
		}
	}
	public String getClubInfo(String id) {
		DataSourceUtils.useDataSource("barhbe",true);
		TeamDetails td = clubDao.findById(id);
		if(td != null) {
			return "Club with id "+td.getId()+" named "+td.getTeamName()+" has been found";
		} else {
			return "Cannot find club with id "+id;
		}
	}
}
