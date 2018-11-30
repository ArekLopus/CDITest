package concurency.test;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import concurency.ConcurrentBeanEJB_Sin;
import concurency.TestBeanConcurrent;

//JMeter and AB test
//ab -c 1 -n 20000 http://localhost:8080/CDITest/res/concurrency/ejb-snl
//ab -c 1 -n 20000 http://localhost:8080/CDITest/res/concurrency/cdi-no-concurrency
//ab -c 1 -n 20000 http://localhost:8080/CDITest/res/concurrency/cdi-synchronized
//ab -c 1 -n 20000 http://localhost:8080/CDITest/res/concurrency/cdi-lock

@Path("concurrency")
public class ConcurrencyTest {
	
	@Inject
	TestBeanConcurrent cb;
	
	@Inject
	ConcurrentBeanEJB_Sin cbe;
	
	@Path("ejb-snl")
	@GET
	public String ejb() {
		return cbe.testMethod2();
	}
	
	@Path("cdi-no-concurrency")
	@GET
	public String cdi1() {
		return cb.testMethod2();
	}
	
	@Path("cdi-synchronized")
	@GET
	public String cdi2() {
		return cb.testMethodSynchronized2();
	}
	
	@Path("cdi-lock")
	@GET
	public String cdi3() {
		return cb.testMethodLocked2();
	}
}
