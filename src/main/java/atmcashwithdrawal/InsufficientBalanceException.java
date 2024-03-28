package atmcashwithdrawal;

@SuppressWarnings("serial")
public class InsufficientBalanceException extends RuntimeException{
	public  InsufficientBalanceException(String message) {
        super(message);
    }
}
