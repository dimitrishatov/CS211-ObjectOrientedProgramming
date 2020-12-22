public class SAccount implements Account {
   public double balance;
   public int acctNumber;
   private static double interestRate = 0.01;

   public SAccount() {
      this.acctNumber = 0;
      this.balance    = 0;
   }

   public SAccount(int acctNumber, double balance) {
      this.acctNumber = acctNumber;
      if (balance <= 0) {
         this.balance = 0.0;
      }
      else {
         this.balance = balance;
      }
   }

   public void withdraw(double amount) {
      if (amount <= 0 || this.balance - amount < 0) {
         this.balance = 0;
      }
      else {
         this.balance -= amount;
      }
   }

   public void deposit(double amount) {
      // Amount must be positive
      if (amount <= 0) {
         return;
      }
      this.balance += amount;
   }

   public int getAccountNumber() {
      return acctNumber;
   }

   public void setAccountNumber(int acctNumber) {
      this.acctNumber = acctNumber;
   }

   public double getBalance() {
      return balance;
   }

   public void setBalance(double balance) {
      if (balance >= 0) {
         this.balance = balance;
      }
   }

   public static double getInterestRate() {
      return interestRate;
   }

   public static void setInterestRate(double interestRate) {
      SAccount.interestRate = interestRate;
   }
}
