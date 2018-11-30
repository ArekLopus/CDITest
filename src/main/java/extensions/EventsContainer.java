package extensions;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.AfterTypeDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.BeforeShutdown;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.ProcessBean;
import javax.enterprise.inject.spi.ProcessBeanAttributes;
import javax.enterprise.inject.spi.ProcessInjectionPoint;
import javax.enterprise.inject.spi.ProcessInjectionTarget;
import javax.enterprise.inject.spi.ProcessManagedBean;
import javax.enterprise.inject.spi.ProcessObserverMethod;
import javax.enterprise.inject.spi.ProcessProducer;
import javax.enterprise.inject.spi.ProcessProducerField;
import javax.enterprise.inject.spi.ProcessProducerMethod;
import javax.enterprise.inject.spi.ProcessSessionBean;
import javax.enterprise.inject.spi.ProcessSyntheticAnnotatedType;
import javax.enterprise.inject.spi.ProcessSyntheticBean;
import javax.enterprise.inject.spi.ProcessSyntheticObserverMethod;

import events.Observers;
import events.transaction_phase.EventTransactionalEJB;
import helper.Coffee;
import helper.Developer;
import produces.producers.ProducersField;
import produces.producers.ProducersPrimitive;

//16.2. Container lifecycle events
//-During the initialization process, the container fires a series of events, including:
// •  BeforeBeanDiscovery
// •  ProcessAnnotatedType and ProcessSyntheticAnnotatedType
// •  AfterTypeDiscovery
// •  ProcessInjectionTarget and ProcessProducer
// •  ProcessInjectionPoint
// •  ProcessBeanAttributes
// •  ProcessBean, ProcessManagedBean, ProcessSessionBean, ProcessProducerMethod, ProcessProducerField and ProcessSyntheticBean
// •  ProcessObserverMethod and ProcessSyntheticObserverMethod
// •  AfterBeanDiscovery
// •  AfterDeploymentValidation
// •  BeforeShutdown

@SuppressWarnings("unused")
public class EventsContainer implements Extension {

	public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd) {
		System.out.println("   BeforeBeanDiscovery event");
	}
	
	public void processAnnotatedType(@Observes ProcessAnnotatedType<Developer> pat) {
        System.out.println("   ProcessAnnotatedType event");
    }
	
	public void afterTypeDiscovery(@Observes AfterTypeDiscovery atd) {
		System.out.println("   AfterTypeDiscovery event");
	}
	// Used at ACTUAL injection time!!! Possible to change injected OBJECT here
	public void processInjectionTarget(@Observes ProcessInjectionTarget<Developer> pit) {
		System.out.println("   ProcessInjectionTarget event");
	}
	
	public void processProducer(@Observes ProcessProducer<ProducersPrimitive, int[]> pp) {
		System.out.println("   ProcessProducer event");
	}
	
	public void processInjectionPoint(@Observes ProcessInjectionPoint<Developer, Coffee> pip) {
		System.out.println("   ProcessInjectionPoint event");
	}
	
	public void processBeanAttributes(@Observes ProcessBeanAttributes<Developer> pba) {
		System.out.println("   ProcessBeanAttributes event");
	}
	
	public void processBean(@Observes ProcessBean<Developer> pb) {
		System.out.println("   ProcessBean event");
	}
	
	public void processManagedBean(@Observes ProcessManagedBean<Developer> pmb) {
		System.out.println("   ProcessManagedBean event");
	}
	
	public void processSessionBean(@Observes ProcessSessionBean<EventTransactionalEJB> psb) {
		System.out.println("   ProcessSessionBean event");
	}

	public void processProducerMethod(@Observes ProcessProducerMethod<int[], ProducersPrimitive> ppm) {
		System.out.println("   ProcessProducerMethod event");
	}

	public void processProducerField(@Observes ProcessProducerField<String, ProducersField> ppf) {
		System.out.println("   ProcessProducerField event");
	}
	
	public void processObserverMethod(@Observes ProcessObserverMethod<StringBuilder, Observers> pom) {
		System.out.println("   ProcessObserverMethod event");
	}
	
	public void afterBeanDiscovery(@Observes AfterBeanDiscovery abd) {
		System.out.println("   AfterBeanDiscovery event");
	}
	
	public void afterDeploymentValidation(@Observes AfterDeploymentValidation adv) {
		System.out.println("   AfterDeploymentValidation event");
	}
	
	public void beforeShutdown(@Observes BeforeShutdown bs) {
		System.out.println("   BeforeShutdown event");
	}
	
	
//	public void processSyntheticAnnotatedType(@Observes ProcessSyntheticAnnotatedType psat) {
//		System.out.println("--------------------   ProcessSyntheticAnnotatedType event");
//	}
//	public void processSyntheticBean(@Observes ProcessSyntheticBean psb) {
//		System.out.println("   ProcessSyntheticBean event");
//	}
//	public void processSyntheticObserverMethod(@Observes ProcessSyntheticObserverMethod psb) {
//		System.out.println("   ProcessSyntheticObserverMethod event");
//	}
	
	
}
