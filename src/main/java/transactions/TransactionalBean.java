package transactions;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;

//-@Transactional provides the application with the ability to control transaction boundaries on CDI Managed Beans,
// as well as Servlets, JAX-RS, and JAX-WS endpoints declaratively. 

@RequestScoped
public class TransactionalBean {
	
	@Resource
	private TransactionSynchronizationRegistry tsr;
	
	
	@Transactional(Transactional.TxType.REQUIRED)	//Default
	public void testTransaction(PrintWriter out) {
		Object transactionKey = tsr.getTransactionKey();
		out.println("Transaction Key: " + transactionKey);
		out.println("<br/>Transaction Status: " + TransactionalBean.getTransactionStatus(tsr.getTransactionStatus()));
		
		tsr.setRollbackOnly();
		out.println("<br/><br/>Transaction Status after setRollbackOnly(): " + TransactionalBean.getTransactionStatus(tsr.getTransactionStatus()));
	}
	
	
	public void testNoTransaction(PrintWriter out) {
		out.println("<br/><br/><br/>No @Transactional, Transaction Status: " + TransactionalBean.getTransactionStatus(tsr.getTransactionStatus()));
	}
	
	
	public static String getTransactionStatus(int code) {
		
		String status = null;
		
		switch (code) {
			case 0: status = "STATUS_ACTIVE"; break;
			case 1: status = "STATUS_MARKED_ROLLBACK"; break;
			case 2: status = "STATUS_PREPARED"; break;
			case 3: status = "STATUS_COMMITTED"; break;
			case 4: status = "STATUS_ROLLEDBACK"; break;
			case 5: status = "STATUS_UNKNOWN"; break;
			case 6: status = "STATUS_NO_TRANSACTION"; break;
			case 7: status = "STATUS_PREPARING"; break;
			case 8: status = "STATUS_COMMITTING"; break;
			case 9: status = "STATUS_ROLLING_BACK"; break;
			default: status = "UNKNOWN"; break;
		}
		
		return status;
	}
}
