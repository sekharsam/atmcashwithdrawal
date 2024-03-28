package atmcashwithdrawal;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class CashWithdraw {

	public static void main(String[] args0) throws InterruptedException {
		AtmActivity atm = new AtmActivity();
		int withdrawalAmount1 = 1600;
		int withdrawalAmount2 = 1800;
		
		Thread t1 = new Thread(new HandleMultipleWithradwal(atm, withdrawalAmount1));
		
		Thread t2 = new Thread(new HandleMultipleWithradwal(atm, withdrawalAmount2));
		
		t1.start();
		
		Thread.sleep(500);
		
		t2.start();

		/*try {
			ConcurrentHashMap<Integer, Integer> noteDispensed = atm.withdraw(withdrawalAmount);
			System.out.println("Withdrawal successful:");

			for (int denomination : noteDispensed.keySet()) {
				System.out.println(noteDispensed.get(denomination) + " x ₹" + denomination);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (InsufficientBalanceException e2) {
			System.out.println(e2.getMessage());
		} catch (InsufficientNoteException e3) {
			System.out.println(e3.getMessage());
		}*/
	}
	
	private static class HandleMultipleWithradwal implements  Runnable{
		AtmActivity atm;
		int withdrawalAmount;
		
		public HandleMultipleWithradwal(AtmActivity atm, int amount) {
			this.atm=atm;
			this.withdrawalAmount = amount;
		}

		@Override
		public void run() {
			try {
				ConcurrentHashMap<Integer, Integer> noteDispensed = atm.withdraw(withdrawalAmount);
				System.out.println("Withdrawal successful:");

				for (int denomination : noteDispensed.keySet()) {
					System.out.println(noteDispensed.get(denomination) + " x ₹" + denomination);
				}
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (InsufficientBalanceException e2) {
				System.out.println(e2.getMessage());
			} catch (InsufficientNoteException e3) {
				System.out.println(e3.getMessage());
			}			
		}
		
	}
}
