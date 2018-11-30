package produces.producers;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Set;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import helper.BeanIntf;
import helper.BeanOne;
import helper.BeanThree;
import helper.BeanTwo;
import helper.PaymentIntf;
import helper.PaymentMastercard;
import helper.PaymentNone;
import helper.PaymentVisa;
import helper.PaymentWiretransfer;
import qualifiers.Payment;
import qualifiers.Payment.PaymentType;
import qualifiers.QualiferBean;
import qualifiers.QualifierString;

public class ProducersPolymorphism {
	
	public static String bean = "none";
	public static PaymentType payment = PaymentType.NONE;
	
	
	@Produces
	@QualiferBean("polymorphismTest")
	public BeanIntf beanProducer(InjectionPoint ip) {
		
		String type = "";
		
		Set<Annotation> qualifiers = ip.getQualifiers();
		
		for (Iterator<Annotation> it = qualifiers.iterator(); it.hasNext(); ) {
			Annotation ann = it.next();
			if(ann.annotationType().getSimpleName().equals(QualiferBean.class.getSimpleName())) {
				QualiferBean ba = (QualiferBean) ann;
				type = ba.type();
			}
	            
	    }
		
		switch (type) {
        	case "one": return new BeanOne();
        	case "two": return new BeanTwo();
        	case "three": return new BeanThree();
        	default: return null;
		}
	}
	
	
	@Produces
	@QualifierString("paymentProducer1")
	public BeanIntf paymentProducer() {
		switch (bean) {
        	case "one": return new BeanOne();
        	case "two": return new BeanTwo();
        	case "three": return new BeanThree();
        	default: return null;
		}
	}
	

	@Produces
	@QualifierString("paymentProducer2")
	public PaymentIntf paymentProducer2(@Payment(PaymentType.VISA) PaymentVisa pv, @Payment(PaymentType.NONE) PaymentNone pn,
			@Payment(PaymentType.MASTERCARD) PaymentMastercard pm, @Payment(PaymentType.WIRE_TRANSFER) PaymentWiretransfer pw ) {

	   switch (payment) {
	      case VISA: return pv;
	      case MASTERCARD: return pm;
	      case WIRE_TRANSFER: return pw;
	      default: return pn;
	   }
	}
	
}
