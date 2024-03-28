Test case couldn't be written properly due to time constraint.
I've written the test case scenario here in a notepad: src/main/resources/TestCaseAssumption

Also, I should've used proper package convention but again due to time constraint I missed that.

CashWithdraw.java is my main class from where the call has happened to 
AtmActivity.java 
where I've kept the checking on fund balance and note sufficiency.

In CashWithdraw.java I've hardcoded 2 input amounts which are being used by 2 threads decalred in this class itself.

In real world the amount would come either through request parameter or through payload but since I didn't make any http request here I had to hardcode the value

I used ConcurrentHashMap to make it thread safe.

I've run the project with hardcoded amount 1600 and 1800 for 2 different threads and it's working.

Building and running the step:
1. mvn clean install
2. mvn package
3. mvn exec:java -Dexec.mainClass=<atmcashwithdrawal.CashWithdraw>
