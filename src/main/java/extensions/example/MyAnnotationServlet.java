package extensions.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/extensionAnn")
public class MyAnnotationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	MyAnnotationBean mb;
	
	@Inject
	MyAnnotationBean2 mb2;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>Extensions Test</h3>");

		out.println("InfoStatic: " + MyAnnotationBean.infoStatic);
		out.println("<br/>Info: " + mb.info);
		
		out.println("<br/><br/>InfoStatic: " + MyAnnotationBean2.infoStatic);
		out.println("<br/>Info: " + mb2.info);
	}
}
