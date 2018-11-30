package transactions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.UserTransaction;

//-The UserTransaction interface defines the methods that allow an application to explicitly manage transaction boundaries.

//-If an attempt is made to call any method of the UserTransaction interface from within the scope of a bean or method
// annotated with @Transactional and a TxType other than NOT_SUPPORTED or NEVER, an IllegalStateException must be thrown.

@WebServlet("/userTransaction")
public class UserTransactionlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource
	UserTransaction ut;
	
	@Resource
	private TransactionSynchronizationRegistry tsr;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Testing UserTransaction</h3>");
		
		testTransaction(out);
	}
	
	public void testTransaction(PrintWriter out) {
		
		try {
			
			ut.begin();
			Object transactionKey = tsr.getTransactionKey();
			out.println("Transaction Key: " + transactionKey);
			out.println("<br/>Transaction Status: " + TransactionalBean.getTransactionStatus(ut.getStatus()));
			
			ut.commit();
			
			out.println("<br/><br/>Transaction Status after ut.commit(): " + TransactionalBean.getTransactionStatus(ut.getStatus()));
			
			ut.begin();
			
			ut.setRollbackOnly();
			out.println("<br/><br/>Transaction Key: " + transactionKey);
			out.println("<br/>Transaction Status after setRollbackOnly(): " + TransactionalBean.getTransactionStatus(ut.getStatus()));
			
			ut.commit();
			
			
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			out.println("<br/><br/>Exception after RollBack : " + e.getMessage());
			out.println("<br/>Exception toString(): " + e.toString());
		}
	}
}
