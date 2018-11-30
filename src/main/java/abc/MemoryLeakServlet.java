package abc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://localhost:8080/CDITest/leak?param=start
//http://localhost:8080/CDITest/leak?param=clear
//http://localhost:8080/CDITest/leak?param=end
@WebServlet("/leak")
public class MemoryLeakServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private List<MemoryLeakTestBean> list = new ArrayList<>();
	private boolean end = false;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Leaker Test</h3>");
		
		String param = request.getParameter("param");
		System.out.println("Param: " + param);
		
		
		if(param != null && param.equals("clear")) {
			list.clear();
			out.println("<br/>Cleared...");
			out.flush();
		}
		
		if(param != null && param.equals("end")) {
			System.out.println("ended");
			end = true;
			out.println("<br/>Ended...");
			out.flush();
			list.clear();
			return;
		}
		
		if(param != null && param.equals("start")) {
			System.out.println("Started");
			end = false;
			out.println("<br/>Started...");
			out.flush();
			leaker();
			return;
		}
		
	}
	
	
	protected void leaker() {
		for(;;) {
			if(end == true) {
				return;
			}
			Instance<MemoryLeakTestBean> tb = CDI.current().select(MemoryLeakTestBean.class);
			MemoryLeakTestBean testBean = tb.get();
			//System.out.println(testBean.callMe());
			list.add(testBean);
		}
		
	}
}
