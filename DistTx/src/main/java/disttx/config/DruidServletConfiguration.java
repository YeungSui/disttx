package disttx.config;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;

@WebServlet(name="druid",urlPatterns= {"/druid/*"},initParams= {
		@WebInitParam(name="loginUsername",value="druid"),
		@WebInitParam(name="loginPassword", value="druid"),
		@WebInitParam(name="resetEnable", value="true")
})
public class DruidServletConfiguration extends StatViewServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
