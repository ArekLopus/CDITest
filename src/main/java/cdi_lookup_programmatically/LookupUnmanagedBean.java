package cdi_lookup_programmatically;

import java.io.IOException;
import java.io.PrintWriter;

import javax.enterprise.inject.spi.Unmanaged;
import javax.enterprise.inject.spi.Unmanaged.UnmanagedInstance;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.TestBeanRequestScoped;

@WebServlet("/unmanagedBean")
public class LookupUnmanagedBean extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h3>Lookup Unmanaged Bean</h3>");
		
		Unmanaged<TestBeanRequestScoped> unmanagedBean = new Unmanaged<>(TestBeanRequestScoped.class);
		UnmanagedInstance<TestBeanRequestScoped> beanInstance = unmanagedBean.newInstance();
		TestBeanRequestScoped bean = beanInstance.produce().inject().postConstruct().get();
		
		String info = bean.callMe();
		
		beanInstance.preDestroy().dispose();
		
		out.println("Unmanaged CDI bean -> " + info);
		
	}
}
