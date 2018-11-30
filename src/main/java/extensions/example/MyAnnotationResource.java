package extensions.example;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("extensions")
public class MyAnnotationResource {
	
	@Inject
	MyAnnotationBean mb;
	
	@Inject
	MyAnnotationBean2 mb2;
	
	@GET
	public String name() {
		return "Info: " + mb.info
				+ "<br/>Info: " + mb2.info;
	}
	
}
