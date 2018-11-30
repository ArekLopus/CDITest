package transactions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;

//-@Transactional provides the application with the ability to control transaction boundaries on CDI Managed Beans,
// as well as Servlets, JAX-RS, and JAX-WS endpoints declaratively. 

@Transactional			//MUST be on class lvl!, Does NOT work on goGet().
@WebServlet("/transactionalServlet")
public class TransactionalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Testing @Transactional Servlet</h3>");
		
		out.println("<br/>Servlet, Transaction: " + tsr.getTransactionKey());
		out.println("<br/><br/>Servlet, Transaction Status: " + TransactionalBean.getTransactionStatus(tsr.getTransactionStatus()));
		
	}
}
