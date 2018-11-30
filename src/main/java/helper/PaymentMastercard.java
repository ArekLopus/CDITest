package helper;

import qualifiers.Payment;
import qualifiers.Payment.PaymentType;

@Payment(PaymentType.MASTERCARD)
public class PaymentMastercard implements PaymentIntf {

	@Override
	public String transfer() {
		return "MasterCard transfer() called.";
	}

}
