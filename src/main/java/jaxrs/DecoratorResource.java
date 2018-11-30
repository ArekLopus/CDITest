package jaxrs;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import cdi.decorator.TalkerIntf;

@Path("decorators")
public class DecoratorResource {
	
	@Inject
	TalkerIntf tk;
	
	@GET
	public String decoratorTest() {
		return "Talker: " + tk.greet();
	}
	

}
