package disttx.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import disttx.utils.AppContextUtils;

@Service
@Transactional()
public class TestService {
	public String testconn() throws SQLException {
		String str = "nothing";
		Connection conn = getDataSource1().getConnection();
		Statement stmt1 = conn.createStatement();
		ResultSet rs = stmt1.executeQuery("select * from user_details where user_id=200");
		if(rs.next()) {
			str = rs.getString(2);
		}
		return str;
	}
	public void addUser(String username, String userid, String desc) throws SQLException {
		Connection conn = getDataSource1().getConnection();
		Statement stmt1 = conn.createStatement();
		stmt1.executeUpdate("insert into user_details(user_id, user_name, description) values ("+userid+",'"+username+"','"+desc+"')");
	}
	
	private DataSource getDataSource1() {
		ApplicationContext ac = AppContextUtils.getApplicationContext();
		Object ds = ac.getBean("primaryDs");
		if(ds instanceof DataSource) {
			return (DataSource)ds;
		}
		return null;
	}
}
