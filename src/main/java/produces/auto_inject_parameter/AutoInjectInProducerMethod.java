package produces.auto_inject_parameter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import cdi.alternative.WorkerIntf;
import qualifiers.QualifierInt;

//-If the producer method has method parameters, the container will look for a bean that satisfies the type and qualifiers
// of each parameter and pass it to the method automatically — another form of dependency injection.
@ApplicationScoped
public class AutoInjectInProducerMethod {
	
	@QualifierInt(222)
	@Produces
	public String test(WorkerIntf wi, InjectionPoint ip) {		//Injected automatically
		return "In producer method, method parameters are injected automatically, " + wi.work()
			+ "<br/><br/>InjectionPoint is also auto injected:"
			+ "<br/>ip.getMember().getDeclaringClass().getName() -> " + ip.getMember().getDeclaringClass().getName()
			+ "<br/>ip.getMember().getDeclaringClass().getSimpleName() -> " + ip.getMember().getDeclaringClass().getSimpleName()
			+ "<br/>ip.getMember().getName() -> " + ip.getMember().getName();
	}
	
}
