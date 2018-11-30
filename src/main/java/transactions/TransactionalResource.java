package transactions;

import javax.annotation.Resource;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//-@Transactional provides the application with the ability to control transaction boundaries on CDI Managed Beans,
// as well as Servlets, JAX-RS, and JAX-WS endpoints declaratively. 

@Path("tansactional")
@Produces(MediaType.TEXT_HTML)
public class TransactionalResource {
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	@Transactional
	@GET
	public String specializedTest() {
		String info = "Transaction Status: " + TransactionalBean.getTransactionStatus(tsr.getTransactionStatus()) 
				+ "<br/>Transaction: " + tsr.getTransactionKey();
		return info;
	}
	
}
