public class CAccount implements Account {
   public double balance;
   public int acctNumber;
   private static double interestRate = 0.05;

   public CAccount() {
      this.balance    = 0;
      this.acctNumber = 0;
   }

   public CAccount(int acctNumber, double balance) {
      this.acctNumber = acctNumber;
      if (balance >= 0) {
         this.balance = balance;
      }
   }

   public void withdraw(double amount) {
      if (amount <= 0) {
         return;
      }

      double withdrawMax = this.balance * 1.1;
      if (amount < withdrawMax) {
         this.balance -= amount + amount * 0.01;
      }
      else {
         this.balance -= withdrawMax;
      }
   }

   public void deposit(double amount) {
      if (amount > 0) {
         this.balance += amount * 0.99;
      }
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
      return CAccount.interestRate;
   }

   public static void setInterestRate(double interestRate) {
      CAccount.interestRate = interestRate;
   }
}
