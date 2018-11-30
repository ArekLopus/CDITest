package cdi.disposes;

import java.io.Serializable;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import qualifiers.QualifierString;

//-You can use a producer method or a producer field to generate an object that
// needs to be removed when its work is completed.
//-If you do, you need a corresponding disposer method, annotated with a @Disposes annotation.

//-The disposer method is called automatically when the context ends.
// (in this case, at the end of the conversation, because RequestBean has conversation scope),
// and the parameter in the close method receives the object produced by the producer field.


@ConversationScoped
public class DisposesTest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Produces
	@QualifierString("@Disposes Test")
	@PersistenceContext
	private EntityManager em;

	public void close(@Disposes @QualifierString("@Disposes Test") EntityManager em) {
	    em.close();
	}
	
}
