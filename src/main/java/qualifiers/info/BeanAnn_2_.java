package qualifiers.info;

import qualifiers.QualifierInt;
import qualifiers.QualifierString;

@QualifierInt
@QualifierString
public class BeanAnn_2_ implements BeanIntf{

	@Override
	public String testMethod() {
		return "Bean 2 Ann's testMethod()";
	}

}
