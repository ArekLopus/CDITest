package transactions.test;

import javax.ejb.Stateless;

@Stateless
public class TransTestEjbCMT {
	
	public String transTest() {
		String info = "This is a string to test in Transactions Test - CMT";
		return info;
	}

}
