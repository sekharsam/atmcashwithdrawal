package atmcashwithdrawal;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AtmActivity {
	
	private static final int[] DENOMINATIONS = {1000, 500, 100};
	private ConcurrentHashMap<Integer, Integer> availableNotes = new ConcurrentHashMap<Integer, Integer>();
	private AtomicInteger balance = new AtomicInteger(10000);
	
	public AtmActivity() {
		availableNotes.put(1000, 10);
		availableNotes.put(500, 5);
		availableNotes.put(100, 20);
	}
	
	 public ConcurrentHashMap<Integer, Integer> withdraw(int amount) {
	        if (amount % 10 != 0) {
	            throw new IllegalArgumentException(ErrorConstants.MULTIPLE_OF_TEN);
	        }

	        if (amount > balance.get()) {
	            throw new InsufficientBalanceException(ErrorConstants.INSUFFICIENT_BALANCE);
	        }

	        ConcurrentHashMap<Integer, Integer> withdrawal = new ConcurrentHashMap<Integer, Integer>();
	        for (int denomination : DENOMINATIONS) {
	            int desiredNotes = amount / denomination;
	            int availableNotesForDenomination = availableNotes.getOrDefault(denomination, 0);
	            int dispensedNotes = Math.min(desiredNotes, availableNotesForDenomination);
	            amount -= dispensedNotes * denomination;
	            withdrawal.put(denomination, dispensedNotes);
	            availableNotes.put(denomination, availableNotesForDenomination - dispensedNotes);
	            if (amount == 0) {
	                break;
	            }
	        }

	        if (amount > 0) {
	            // Insufficient notes to dispense full amount
	            throw new InsufficientNoteException(ErrorConstants.INSUFFICIENT_NOTE);
	        }

	        balance.addAndGet(-amount); //update balance automatically
	        return withdrawal;
	    }

}
