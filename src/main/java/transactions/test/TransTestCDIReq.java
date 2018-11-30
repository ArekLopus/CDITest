package transactions.test;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

@RequestScoped
public class TransTestCDIReq {
	
	@Transactional
	public String transTest() {
		String info = "This is a string to test in Transactions Test - CDI Req";
		return info;
	}

}
