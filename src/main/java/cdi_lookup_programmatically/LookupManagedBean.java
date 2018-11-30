package cdi_lookup_programmatically;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.spi.CDI;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.TestBeanRequestScoped;

@WebServlet("/managedBean")
public class LookupManagedBean extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Lookup Managed Bean</h3>");
		
		TestBeanRequestScoped bean = CDI.current().select(TestBeanRequestScoped.class).get();
		
		String info = bean.callMe();
		
		out.println("Managed CDI bean -> " + info);
		
		//Not needed, it is destroyed with this request scoped bean. 
		CDI.current().destroy(bean);
		
	}
}
