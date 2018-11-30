package transactions.test;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class TransTestCDIApp {
	
	@Transactional
	public String transTest() {
		String info = "This is a string to test in Transactions Test - CDI App";
		return info;
	}

}
