package produces;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import produces.producers.ProducersPrimitive;
import qualifiers.QualifierString;

//-The objects may require some custom initialization that is not performed by the bean constructor.
//http://localhost:8080/CDITest/producesRandomInt?seed=100
@WebServlet("/producesRandomInt")
public class RuntimeCustomInitialization extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierString("randomizer")
	Instance<Integer> randomInt;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>The objects may require some custom initialization that is not performed by the bean constructor.</h3>");
		out.println("<h4>Use query parameter 'seed' to change random seed, for example '?seed=100' .</h4>");
		
		String param = request.getParameter("seed");
		if(param == null) 
			ProducersPrimitive.seed = 1;
		
		try {
			int seed = Integer.parseInt(param);
			ProducersPrimitive.seed = seed;
		} catch (Exception e) {
			ProducersPrimitive.seed = 1;
		}
		
		out.println("Next random int: " + randomInt.get());
		ProducersPrimitive.seed = 1;
	}
	

	
}
