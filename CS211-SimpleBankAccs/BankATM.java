import java.util.ArrayList;

public class BankATM {
   private ArrayList<Account> accounts;

   public BankATM() {
      accounts = new ArrayList<Account>();
   }

   public BankATM(int numberAccounts) {
      accounts = new ArrayList<Account>(numberAccounts);
   }

   public BankATM(ArrayList<Account> accounts) {
      this.accounts = accounts;
   }

   public ArrayList<Account> getAccount() {
      return accounts;
   }

   public void setAccount(ArrayList<Account> accounts) {
      this.accounts = accounts;
   }

   public void add(Account account) {
      accounts.add(account);
   }

   public boolean remove(Account account) {
      return accounts.remove(account);
   }

   public void calcInterest() {
      for (Account userAcc : accounts) {
         if (userAcc instanceof SAccount) {
            SAccount account = (SAccount) userAcc;
            account.balance += account.balance * SAccount.getInterestRate();
         } else if (userAcc instanceof CAccount) {
            CAccount account = (CAccount) userAcc;
            account.balance += account.balance * CAccount.getInterestRate();
         }
      }
   }
}
