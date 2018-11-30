package injection.point;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import qualifiers.QualifierString;

public class IpProducer {

	@Produces
	@QualifierString("ip")
	public String abc(InjectionPoint ip) {
		ip.getType();
		ip.getQualifiers();
		ip.getAnnotated();
		ip.getBean();
		ip.getMember().getName();
		ip.getMember().getClass();
		ip.getMember().getDeclaringClass();
		ip.getMember().getModifiers();
		ip.getMember().isSynthetic();
		ip.isTransient();
		ip.isDelegate();
		String s = "<br/>ip.getMember().getName() -> "+ip.getMember().getName()
				+ "<br/>ip.getMember().getClass() -> "+ip.getMember().getClass()
				+ "<br/>ip.getMember().getDeclaringClass() -> "+ip.getMember().getDeclaringClass()
				+ "<br/>ip.getMember().getModifiers() -> "+ip.getMember().getModifiers()
				+ "<br/>ip.getMember().isSynthetic() -> "+ip.getMember().isSynthetic()
				+ "<br/>ip.isTransient() -> "+ip.isTransient()
				+ "<br/>ip.isDelegate() -> "+ip.isDelegate()
				+ "<br/>ip.getType() -> "+ip.getType()
				+ "<br/>ip.getQualifiers() -> "+ip.getQualifiers()
				+ "<br/>ip.getAnnotated() -> "+ip.getAnnotated()
				+ "<br/>ip.getBean() -> "+ip.getBean();
				
		return s;
	}
	
}
