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

@WebServlet(value="/concurrencyLockCDI")
public class LockCDIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	TestBeanConcurrent cb;
	
	@Resource
	ManagedExecutorService mes;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Concurrency LockCDI</h3>");
		

		testConcurrency(cb::testMethodLocked);
		
		//testConcurrency(cb::testMethodSynchronized);
		
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
