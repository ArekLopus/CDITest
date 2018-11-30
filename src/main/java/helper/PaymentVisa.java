package helper;

import qualifiers.Payment;
import qualifiers.Payment.PaymentType;

@Payment(PaymentType.VISA)
public class PaymentVisa implements PaymentIntf {

	@Override
	public String transfer() {
		return "Visa transfer() called.";
	}

}
