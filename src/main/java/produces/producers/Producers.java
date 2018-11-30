package produces.producers;

import java.util.concurrent.ThreadLocalRandom;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import qualifiers.QualifierInt;
import qualifiers.QualifierString;
import qualifiers.QualifierWithNoMemebers;

public class Producers {
	
	@Produces
	@QualifierString("fieldNameAsAKeyProducer")
	public String resultDependsOnTheFieldName(InjectionPoint ip) {
		String name = ip.getMember().getName();
		return name;
	}
	
	
	@Produces
	@QualifierString("stringProducer")
	public String stringProducer() {
		return "String produced with @QualifierString";
	}
	
	@Produces
	@QualifierWithNoMemebers
	public String stringProducerNoMemebers() {
		return "String produced with @QualifierWithNoMemebers";
	}
	
	@Produces
	@QualifierString("doubleQualifiersProducer")
	@QualifierInt(142)
	public String doubleQualifiersProducer() {
		return "String produced with double qualifiers @QualifierString @QualifierInt";
	}
	
	@Produces
	public long longProducer() {
		return ThreadLocalRandom.current().nextLong(100);
	}

	
}
