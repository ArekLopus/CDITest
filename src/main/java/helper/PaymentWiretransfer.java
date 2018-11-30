package helper;

import qualifiers.Payment;
import qualifiers.Payment.PaymentType;

@Payment(PaymentType.WIRE_TRANSFER)
public class PaymentWiretransfer implements PaymentIntf {

	@Override
	public String transfer() {
		return "Wiretransfer transfer() called.";
	}

}
