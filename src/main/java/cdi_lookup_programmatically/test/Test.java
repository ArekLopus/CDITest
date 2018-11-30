package cdi_lookup_programmatically.test;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

//-JAX-RS Endpoint is created per request, Servlet is one per application.
//-Both for testing diffrences in both approaches and effect of multi-threads

//ab -c 1 -n 20000 http://localhost:8080/CDITest/res/lookup/injected-req
//ab -c 5 -n 50000 http://localhost:8080/CDITest/res/lookup/injected-req

//ab -c 1 -n 20000 http://localhost:8080/CDITest/res/lookup/injected-app
//ab -c 1 -n 20000 http://localhost:8080/CDITest/res/lookup/lookedup-req
//ab -c 1 -n 20000 http://localhost:8080/CDITest/res/lookup/lookedup-app
@Path("lookup")
public class Test {
	
	@Inject
	TestBeanReq rbi;
	
	@Inject
	TestBeanApp abi;
	
	Instance<TestBeanReq> rbl;
	Instance<TestBeanApp> abl;
	
	@Path("injected-req")
	@GET
	public String inject() {
		return rbi.callMe();
	}
	
	@Path("injected-app")
	@GET
	public String injectApp() {
		return abi.callMe();
	}
	
	@Path("lookedup-req")
	@GET
	public String lokkedup() {
		return CDI.current().select(TestBeanReq.class).get().callMe();
	}
	@Path("lookedup-req2")
	@GET
	public String lokkedup2() {
		rbl = CDI.current().select(TestBeanReq.class);
		return rbl.get().callMe();
	}
	
	@Path("lookedup-app")
	@GET
	public String lokkedupApp() {
		return CDI.current().select(TestBeanApp.class).get().callMe();
	}
	@Path("lookedup-app2")
	@GET
	public String lokkedupApp2() {
		abl = CDI.current().select(TestBeanApp.class);
		return abl.get().callMe();
	}
}
