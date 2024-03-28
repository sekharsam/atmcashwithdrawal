package atmcashwithdrawal;

@SuppressWarnings("serial")
public class InsufficientNoteException extends RuntimeException{
	public  InsufficientNoteException(String message) {
        super(message);
    }
}
