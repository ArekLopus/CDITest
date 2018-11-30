package extensions.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/integrateMyBean")
public class IntegrateMyBeanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	IntegrateMyBean mb;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>Extensions Test - Simple new bean integration.</h3>");
		
		BeanManager bm = CDI.current().getBeanManager();
		
		Instance<IntegrateMyBean> selected = CDI.current().select(IntegrateMyBean.class);
		IntegrateMyBean integrateMyBean = selected.get();
		

		Set<Bean<?>> beans = bm.getBeans(IntegrateMyBean.class);
		Bean<?> bean = beans.iterator().next();
		
		System.out.println("Name: " + bean.getName());
		System.out.println("Class: " + bean.getBeanClass());
		System.out.println("Qualifiers: " + bean.getQualifiers());
		System.out.println("Scope: " + bean.getScope());
		
		
		out.println("<br/>Injected, Info: " + mb.getInfo());
		out.println("<br/>Looked up, Info: " + integrateMyBean.getInfo());
	}
}
