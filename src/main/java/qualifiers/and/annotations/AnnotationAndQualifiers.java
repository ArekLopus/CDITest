package qualifiers.and.annotations;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;

import qualifiers.QualifierInt;
import qualifiers.QualifierString;


public class AnnotationAndQualifiers {
	
	QualifierInt ann;
	
	@Produces
	@QualifierInt(16435)
	@QualifierString("JustToTest")
	public String abc(InjectionPoint ip) {
		
		Set<Annotation> qualifiers = ip.getQualifiers();
		qualifiers.forEach( e -> {
			if(e instanceof QualifierInt) {
				ann = (QualifierInt) e;
			}
		});
		
		int value = ann.value();
		
		Annotated annotated = ip.getAnnotated();
		Set<Annotation> annotations = annotated.getAnnotations();
		
		
		String info = "Qualifiers: " + qualifiers.toString() + ",<br/>value -> " + value
				+ "<br><br/>Annotations: " + annotations.toString();
		
		return info;
	}
	
	
}
