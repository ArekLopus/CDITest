package jaxrs;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import cdi.typed.ClassOne;
import cdi.typed.ClassTwo;

@Path("typed")
public class TypedResource {
	
	@Inject
	ClassOne cl;
	
	@Inject
	ClassTwo cl2;
	
	@GET
	public String alternativeTest() {
		return "ClassOne: " + cl.classTest() + ", ClassTwo: " + cl2.classTest();
	}

}
