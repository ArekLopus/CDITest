package concurency;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-CDI don't have concurrency management.
//-@ConcurrencyManagement and @Lock are only available on singleton session beans.
//-@Statefull - is thread safe but @ConcurrencyManagement doesnt work.
//-@Stateless uses a different bean for each call (one bean per thread, so it is thread safe).


//-@Singleton - serialized
//-@Singleton with @Lock(LockType.READ) on method - NOT serialized () (turns off @Lock(LockType.WRITE))
//-@Singleton with @Lock(LockType.WRITE) on method - serialized

//-@Singleton BMC - NOT serialized (@ConcurrencyManagement works)
//-@Singleton BMC with @Lock(LockType.READ) on method - NOT serialized
//!!-@Singleton BMC with @Lock(LockType.WRITE) on method - NOT serialized - @Lock DOES NOT WORK! need to protect it manually!!!

//-@Stateless uses a different bean for each call (so it is 'serialized').

//-@Stateful - serialized (1 object used for all calls - concurency.ConcurrentBeanEJB@1256f5f)
//-@Stateful with @ConcurrencyManagement(BEAN) - serialized (@ConcurrencyManagement works only for @Singleton)
//-@Statefulwith @Lock(LockType.READ) on method - serialized
//-@Statefulwith @Lock(LockType.WRITE) on method - serialized 

//-TestBeanConcurrent.testMethod() is not thread safe.
//-TestBeanConcurrent.testMethodSynchronized() is serialized.
//-TestBeanConcurrent.testMethodLocked() is serialized.

//http://localhost:8080/CDITest/concurrency?type=cdi-test
//http://localhost:8080/CDITest/concurrency?type=cdi-sync
//http://localhost:8080/CDITest/concurrency?type=cdi-lock
@WebServlet(value="/concurrency")
public class ConcurrencyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	TestBeanConcurrent cb;
	
	@Inject
	ConcurrentBeanEJB_Sin cbes;
	
	@Inject
	ConcurrentBeanEJB_Sin_BMC cbesb;
	
	@Inject
	ConcurrentBeanEJB_SL cbesl;
	
	@Inject
	ConcurrentBeanEJB_SF cbesf;
	
	@Resource
	ManagedExecutorService mes;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Concurrency</h3>");
		
		String param = request.getParameter("type");
		if(param == null) {
			out.println("Pick a type, cdi: ?type=cdi-test ?type=cdi-sync ?type=cdi-lock");
			out.println("<br/><br/>Pick a type, ejb singleyon: ?type=ejb-sin-test ?type=ejb-sin-read ?type=ejb-sin-write");
			out.println("<br/>Pick a type, ejb singleyon bmc: ?type=ejb-sin-bmc-test ?type=ejb-sin-bmc-read ?type=ejb-sin-bmc-write");
			out.println("<br/>Pick a type, ejb stateless: ?type=ejb-sl-test ?type=ejb-sl-read ?type=ejb-sl-write");
			out.println("<br/>Pick a type, ejb stateless: ?type=ejb-sf-test ?type=ejb-sf-read ?type=ejb-sf-write");
			return;
		}
		
		switch (param) {
			case "cdi-test": testConcurrency(cb::testMethod); break;
			case "cdi-sync": testConcurrency(cb::testMethodSynchronized); break;
			case "cdi-lock": testConcurrency(cb::testMethodLocked); break;
			
			case "ejb-sin-test": testConcurrency(cbes::testMethod); break;
			case "ejb-sin-read": testConcurrency(cbes::testMethodWithLockRead); break;
			case "ejb-sin-write": testConcurrency(cbes::testMethodWithLockWrite); break;
			
			case "ejb-sin-bmc-test": testConcurrency(cbesb::testMethod); break;
			case "ejb-sin-bmc-read": testConcurrency(cbesb::testMethodWithLockRead); break;
			case "ejb-sin-bmc-write": testConcurrency(cbesb::testMethodWithLockWrite); break;
			
			case "ejb-sl-test": testConcurrency(cbesl::testMethod); break;
			case "ejb-sl-read": testConcurrency(cbesl::testMethodWithLockRead); break;
			case "ejb-sl-write": testConcurrency(cbesl::testMethodWithLockWrite); break;
		
			case "ejb-sf-test": testConcurrency(cbesf::testMethod); break;
			case "ejb-sf-read": testConcurrency(cbesf::testMethodWithLockRead); break;
			case "ejb-sf-write": testConcurrency(cbesf::testMethodWithLockWrite); break;
		
			default: break;
		}
		
	}
	
	private void testConcurrency(Runnable r) {	//Runnable only to use type: () -> {} 
		System.out.println("------------------------------------");
		
		for(int i = 0; i < 3; i++) {
			mes.execute(() -> {
				r.run();
			});
		}
		
	}
	
}
