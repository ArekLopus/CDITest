package transactions.test;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@ApplicationScoped
public class TransTestCDIAppUserTr {
	
	@Resource
	UserTransaction ut;
	
	public String transTest() throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, NotSupportedException {
		ut.begin();
		String info = "This is a string to test in Transactions Test - CDI App UT";
		ut.commit();
		return info;
	}

}
