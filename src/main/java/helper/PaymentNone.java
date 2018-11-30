package helper;

import qualifiers.Payment;
import qualifiers.Payment.PaymentType;

@Payment(PaymentType.NONE)
public class PaymentNone implements PaymentIntf {

	@Override
	public String transfer() {
		return "None transfer() called.";
	}

}
