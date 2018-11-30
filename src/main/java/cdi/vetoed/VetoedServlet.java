package cdi.vetoed;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.CDI;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-Veto the processing of the class. Any beans or observer methods defined by this class will not be installed.
//-When placed on package, all beans in the package are prevented from being installed.
// If packages are split across jars, non-portable behavior results.
// An application can prevent packages being split across jars by sealing the package.

//-No container lifecycle events are fired for classes annotated Vetoed.

@WebServlet("/vetoed")
public class VetoedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>@Vetoed - vetoes the processing of the class.</h3>");
		
		Instance<VetoedTest> selected = CDI.current().select(VetoedTest.class);
		out.println("VetoedTest class is vetoed so we cant get it: " + selected.stream().collect(Collectors.toList()).size());
	}
	
}
