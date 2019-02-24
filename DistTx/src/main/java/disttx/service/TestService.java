package disttx.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import disttx.utils.AppContextUtils;
import oracle.sql.TIMESTAMP;

@Service
@Transactional()
public class TestService {
	public String testconn() throws SQLException {
		String str = "nothing";
		Connection conn2 = getDataSource2().getConnection();
		PreparedStatement stmt2 = conn2.prepareStatement("insert into test_log(id, logtime, threadinfo) values(?,?,?)");
		stmt2.setString(1, UUID.randomUUID().toString().replaceAll("-", ""));
		stmt2.setTimestamp(2, new Timestamp(new Date().getTime()));
		stmt2.setString(3, ""+Thread.currentThread().getId());
		stmt2.executeUpdate();
		conn2.close();
		Connection conn1 = getDataSource1().getConnection();
		Statement stmt1 = conn1.createStatement();
		ResultSet rs = stmt1.executeQuery("select * from user_detail where user_id=200");
		if(rs.next()) {
			str = rs.getString(2);
		}
		conn1.close();
		return str;
	}
	public void addUser(String username, String userid, String desc) throws SQLException {
		Connection conn = getDataSource1().getConnection();
		Statement stmt1 = conn.createStatement();
		stmt1.executeUpdate("insert into user_details(user_id, user_name, description) values ("+userid+",'"+username+"','"+desc+"')");
		conn.close();
	}
	
	private DataSource getDataSource1() {
		ApplicationContext ac = AppContextUtils.getApplicationContext();
		Object ds = ac.getBean("primaryDs");
		if(ds instanceof DataSource) {
			return (DataSource)ds;
		}
		return null;
	}
	
	private DataSource getDataSource2() {
		ApplicationContext ac = AppContextUtils.getApplicationContext();
		if(ac != null) {
			Object ds = ac.getBean("secondaryDs");
			if(ds instanceof DataSource) {
				return (DataSource) ds;
			}
		}
		return null;
	}
}
