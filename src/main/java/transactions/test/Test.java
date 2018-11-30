package transactions.test;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

//ab -c 1 -n 20000 http://localhost:8080/CDITest/res/trans/ejb-ctm
//ab -c 1 -n 20000 http://localhost:8080/CDITest/res/trans/ejb-btm
//ab -c 1 -n 20000 http://localhost:8080/CDITest/res/trans/cdi-app
//ab -c 1 -n 20000 http://localhost:8080/CDITest/res/trans/cdi-req
//ab -c 1 -n 20000 http://localhost:8080/CDITest/res/trans/cdi-app-ut
//ab -c 1 -n 20000 http://localhost:8080/CDITest/res/trans/cdi-req-ut
@Path("trans")
public class Test {
	
	@Inject
	TransTestEjbCMT tec;
	
	@Inject
	TransTestEjbBMT teb;
	
	@Inject
	TransTestCDIApp tca;
	
	@Inject
	TransTestCDIReq tcr;
	
	@Inject
	TransTestCDIAppUserTr tcau;
	
	@Inject
	TransTestCDIReqUserTr tcru;
	
	@GET
	@Path("ejb-ctm")
	public String testEjbCMT() {
		return tec.transTest();
	}
	
	@GET
	@Path("ejb-btm")
	public String testEjbBMT() throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, NotSupportedException {
		return teb.transTest();
	}
	
	@GET
	@Path("cdi-app")
	public String testCDIapp() {
		return tca.transTest();
	}
	
	@GET
	@Path("cdi-req")
	public String testCDIreq() {
		return tcr.transTest();
	}
	
	@GET
	@Path("cdi-app-ut")
	public String testCDIappUT() throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, NotSupportedException {
		return tcau.transTest();
	}
	
	@GET
	@Path("cdi-req-ut")
	public String testCDIreqUT() throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, NotSupportedException {
		return tcru.transTest();
	}
}
