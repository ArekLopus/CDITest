package qualifiers.info;

import javax.enterprise.inject.Produces;

import qualifiers.QualifierInt;
import qualifiers.QualifierString;

public class Producers {
	

	@Produces
	public StringBuilder sbProducerNoQualifier() {
		return new StringBuilder("StringBuilder with no Qualifiers");
	}
	
	@Produces
	@QualifierString
	public StringBuilder sbProducerOneQualiferString() {
		return new StringBuilder("StringBuilder with 1s Qualifier");
	}
	
	@Produces
	@QualifierInt
	public StringBuilder sbProducerOneQualiferInt() {
		return new StringBuilder("StringBuilder with 1i Qualifier");
	}
	
	@Produces
	@QualifierString
	@QualifierInt
	public StringBuilder sbProducerTwoQualifers() {
		return new StringBuilder("StringBuilder with 2 Qualifiers");
	}
	
}
