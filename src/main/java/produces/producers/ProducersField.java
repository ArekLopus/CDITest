package produces.producers;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import qualifiers.QualifierString;

public class ProducersField {
	
	
	@Produces
	@QualifierString("fieldProducer")
	private String fldPcr = "This String is produced by a filed.";
	
	
	@Produces
	@QualifierString("ComponentEnvironmentResources")
	@PersistenceContext
	EntityManager em;
	
	public void close(@Disposes @QualifierString("ComponentEnvironmentResources") EntityManager em) {
		System.out.println("@Disposes @QualifierString('ComponentEnvironmentResources') called.");
	    em.close();
	}
	
}