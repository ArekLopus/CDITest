package cdi.decorator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-The decorator bean class implements an interface and defines, through a delegate injection point
//(annotated with @Delegate),which field, method or constructor needs to be decorated.
//-A decorator must have exactly one delegate injection point
//-A decorator must be explicitly enabled under the <decorators> element of the beans.xml file of the bean archive.
//	<decorators>
//		<class>cdi.decorators.TalkerDecorated</class>
//	</decorators>

//-From CDI 1.1 onwards the decorator can be enabled for the whole application using @Priority annotation.

//-Decorators are called after interceptors.

//-If no @Delegate defined:
//org.glassfish.deployment.common.DeploymentException: CDI definition failure:WELD-000059: No delegate injection point defined
//for [EnhancedAnnotatedTypeImpl] public @Decorator class cdi.decorators.TalkerDecorated -- WELD-000059:
//No delegate injection point defined for [EnhancedAnnotatedTypeImpl] public @Decorator class cdi.decorators.TalkerDecorated

//@Decorated
//-A decorator may inject metadata about the bean it is decorating
// @Inject
// @Decorated
// Bean<Logger> bean;

@WebServlet("/decorator")
public class DecoratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	TalkerIntf tk;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>@Decorator</h3>");
		
		out.println("Talker: " + tk.greet());
		
	}
	
}
