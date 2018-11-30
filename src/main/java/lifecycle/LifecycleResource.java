package lifecycle;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("lifecycle")
public class LifecycleResource {
	
	@Inject
	LifecycleTestBean lc;
	
	@GET
	public String doTest() {
		
		lc.callMe();
		
		return "lifecycle test";
	}
}
