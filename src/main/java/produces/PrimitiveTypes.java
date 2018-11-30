package produces;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import produces.producers.ProducersPrimitive;
import qualifiers.QualifierString;

//-Producer can return a primitive type or a Java array type,
//-Producer methods and fields may have a primitive bean type.
//-For the purpose of resolving dependencies,
// primitive types are considered to be identical to their corresponding wrapper types in java.lang.
@WebServlet("/producesPrimitiveTypes")
public class PrimitiveTypes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@QualifierString("randomizer")
	Instance<Integer> randomInt;
	
	@Inject
	@QualifierString("primitiveArray")
	Instance<int[]> array;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Producer methods and fields may have a primitive bean type.</h3>"); 
				
		out.println("For the purpose of resolving dependencies, primitive types are considered"
				+ " to be identical to their corresponding wrapper types in java.lang.Randomizer returns an int.<br/>");
		
		ProducersPrimitive.seed = 100;
		
		out.println("<br/><br/>Next random int: " + randomInt.get());
		ProducersPrimitive.seed = 1;
		
		out.println("<br/><br/>Ranodom int array: " + Arrays.toString(array.get()));
	}
	

	
}
