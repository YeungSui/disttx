package disttx.generic.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppContextUtils implements ApplicationContextAware {
	private static ApplicationContext ac;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		if(applicationContext != null) {
			ac = applicationContext;
		}
	}
	public static ApplicationContext getApplicationContext() {
		return ac;
	}
}
