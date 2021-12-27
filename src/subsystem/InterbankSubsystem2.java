package subsystem;

import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.interbank.InterbankSubsystemController2;

/***
 * The {@code InterbankSubsystem} class is used to communicate with the
 * Interbank to make transaction.
 * 
 * @author DuongVB
 *
 */
public class InterbankSubsystem2 implements InterbankInterface {

	/**
	 * Represent the controller of the subsystem
	 */
	private InterbankSubsystemController2 ctrl;

	/**
	 * Initializes a newly created {@code InterbankSubsystem} object so that it
	 * represents an Interbank subsystem.
	 */
	public InterbankSubsystem2() {
		String str = new String();
		this.ctrl = new InterbankSubsystemController2();
	}

	/**
	 * @see InterbankInterface#payOrder(entity.payment.CreditCard, int,
	 *      java.lang.String)
	 */
	public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
		PaymentTransaction transaction = ctrl.payOrder(card, amount, contents);
		return transaction;
	}

	/**
	 * @see InterbankInterface#refund(entity.payment.CreditCard, int,
	 *      java.lang.String)
	 */
	public PaymentTransaction refund(CreditCard card, int amount, String contents) {
		PaymentTransaction transaction = ctrl.refund(card, amount, contents);
		return transaction;
	}
}
