import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class E5Tester {
   public static void main(String args[]){
      org.junit.runner.JUnitCore.main("E5Tester");
   }


   static final double ERROR = 0.000001;
   //*************************************Interface Tests***************


   @Test(timeout=1000) public void Account_Interface_test_00() {



      Account sa = new SAccount();
      Account ca = new CAccount();
      SAccount sa2 = new SAccount();
      CAccount ca2 = new CAccount();


      assertTrue("Account interface is not implemented correctly in SAccount class", sa instanceof Account);
      assertTrue("Account interface is not implemented correctly in SAccount class", sa2 instanceof Account);
      assertTrue("Account interface is not implemented correctly in CAccount class", ca instanceof Account);
      assertTrue("Account interface is not implemented correctly in CAccount class", ca2 instanceof Account);

   }

   //*****************************************SAccount Testers**************************
   @Test(timeout=1000) public void SAccount_Constructor_test_00() {

      SAccount sa = new SAccount();
      assertEquals("Default constructor (SAccount) is incorrect", 0, sa.balance, 0.0001);
      assertEquals("Default constructor (SAccount) is incorrect", 0, sa.acctNumber);

   }


   @Test(timeout=1000) public void SAccount_Constructor_test_01() {

      SAccount sa = new SAccount(12, 458.95);
      assertEquals("Non-Default constructor (SAccount) is incorrect", 458.95, sa.balance, 0.0001);
      assertEquals("non-Default constructor (SAccount) is incorrect", 12, sa.acctNumber);

   }
   //double[] bal = {1588.98, 225.36,100006,12125698, -25552};

   double sBal[] = {1000, 5897.56,256987,4588, 0,-98939,26.5};

   double sRBal[] = {1000, 5897.56,256987,4588, 0,0 ,26.5};

   double wSBal[]= {1500, 1250,256987, 4600, 10,5, 22.1};

   double dSBal[] =  {0, 1255, -25, 4589, -22.598, 2387, 45.55};

   double sWResult[] = {0.0, 4647.56, 0.0, 0.0, 0.0, 0.0, 4.399999999999999};

   double sDResult[] = {1000.0, 7152.56, 256987.0, 9177.0, 0.0, 2387.0, 72.05};

   void checkDeposit_SAccount(int a) {
      SAccount c = null;
      c = new SAccount(001,sBal[a]);
      c.deposit(dSBal[a]);

      String errMsg = String.format("deposit(%f) for SAccount incorrect", dSBal[a]);
      assertEquals(errMsg, sDResult[a], c.getBalance(), ERROR);
   }

   @Test(timeout=1000) public void checkDeposit_SAccount_00() { checkDeposit_SAccount(0); }
   @Test(timeout=1000) public void checkDeposit_SAccount_01() { checkDeposit_SAccount(1); }
   @Test(timeout=1000) public void checkDeposit_SAccount_02() { checkDeposit_SAccount(2); }
   @Test(timeout=1000) public void checkDeposit_SAccount_03() { checkDeposit_SAccount(3); }
   @Test(timeout=1000) public void checkDeposit_SAccount_04() { checkDeposit_SAccount(4); }
   @Test(timeout=1000) public void checkDeposit_SAccount_05() { checkDeposit_SAccount(5); }


   void checkWithdraw_SAccount(int a) {
      SAccount c = null;
      c = new SAccount(001,sBal[a]);
      c.withdraw(wSBal[a]);

      String errMsg = String.format("withdraw(%f) for SAccount incorrect", sBal[a]);
      assertEquals(errMsg, sWResult[a], c.getBalance(), ERROR);
   }

   @Test(timeout=1000) public void checkWithdraw_SAccount_00() { checkWithdraw_SAccount(0); }
   @Test(timeout=1000) public void checkWithdraw_SAccount_01() { checkWithdraw_SAccount(1); }
   @Test(timeout=1000) public void checkWithdraw_SAccount_02() { checkWithdraw_SAccount(2); }
   @Test(timeout=1000) public void checkWithdraw_SAccount_03() { checkWithdraw_SAccount(3); }
   @Test(timeout=1000) public void checkWithdraw_SAccount_04() { checkWithdraw_SAccount(4); }
   @Test(timeout=1000) public void checkWithdraw_SAccount_05() { checkWithdraw_SAccount(5); }


   double balResult[]= { 1000.0, 5897.56, 256987.0, 4588.0, 0.0, 0.0, 26.5};

   void checkGetBalance_SAccount(int a) {
      SAccount c = null;
      c = new SAccount(001,sBal[a]);
      // c.withdraw(wCBal[a]);

      String errMsg = String.format("getBalance() for SAccount incorrect");
      assertEquals(errMsg, sRBal[a], c.getBalance(), ERROR);
   }

   @Test(timeout=1000) public void checkGetBalance_SAccount_00() { checkGetBalance_SAccount(0); }
   @Test(timeout=1000) public void checkGetBalance_SAccount_01() { checkGetBalance_SAccount(1); }
   @Test(timeout=1000) public void checkGetBalance_SAccount_02() { checkGetBalance_SAccount(2); }
   @Test(timeout=1000) public void checkGetBalance_SAccount_03() { checkGetBalance_SAccount(3); }
   @Test(timeout=1000) public void checkGetBalance_SAccount_04() { checkGetBalance_SAccount(4); }
   @Test(timeout=1000) public void checkGetBalance_SAccount_05() { checkGetBalance_SAccount(5); }

   void checkSetBalance_SAccount(int a) {
      SAccount c = null;
      c = new SAccount();
      c.setBalance(sBal[a]);

      String errMsg = String.format("setBalance(%f) for SAccount incorrect", sBal[a]);
      assertEquals(errMsg, sRBal[a], c.getBalance(), ERROR);
   }
   @Test(timeout=1000) public void checkSetBalance_SAccount_00() { checkSetBalance_SAccount(0); }
   @Test(timeout=1000) public void checkSetBalance_SAccount_01() { checkSetBalance_SAccount(1); }
   @Test(timeout=1000) public void checkSetBalance_SAccount_02() { checkSetBalance_SAccount(2); }
   @Test(timeout=1000) public void checkSetBalance_SAccount_03() { checkSetBalance_SAccount(3); }
   @Test(timeout=1000) public void checkSetBalance_SAccount_04() { checkSetBalance_SAccount(4); }
   @Test(timeout=1000) public void checkSetBalance_SAccount_05() { checkSetBalance_SAccount(5); }

   @Test(timeout=1000) public void checkSetGetInterestRate_SAccount_00() {
      SAccount c = new SAccount();

      String errMsg = String.format("getInterestRate() for SAccount incorrect");
      assertEquals(errMsg, 0.01, SAccount .getInterestRate(), ERROR);
      SAccount .setInterestRate(0.02);
      String errMsg2 = String.format("setInterestRate() for SAccount incorrect");
      assertEquals(errMsg2, 0.02, SAccount .getInterestRate(), ERROR);
   }

   //*************************************CAccount Tests***************
   @Test(timeout=1000) public void CAccount_Constructor_test_00() {

      CAccount ca = new CAccount();
      assertEquals("Default constructor (CAccount) is incorrect", 0, ca.balance, ERROR);
      assertEquals("Default constructor (CAccount) is incorrect", 0, ca.acctNumber);

   }


   @Test(timeout=1000) public void CAccount_Constructor_test_01() {

      CAccount ca = new CAccount(12, 458.95);
      assertEquals("Non-Default constructor (CAccount) is incorrect", 458.95, ca.balance, ERROR);
      assertEquals("non-Default constructor (CAccount) is incorrect", 12, ca.acctNumber);

   }

   double cBal[] = {1000, 5897.56,256987,4588, 0,-98939,26.5};

   double wCBal[]= {1500, 1250,256987, 4600, 10,5, 22.1};

   double dCBal[] =  {0, 1255, -25, 4589, -22.598, 2387, 45.55};

   double cWResult[] = {-100.00, 4635.06, -2569.8699999999953, -58.0, 0.0, 0.0, 4.1789999999999985};
   double cDResult[]= {1000.0, 7140.01, 256987.0, 9131.11, 0.0, 2363.13, 71.5945};


   void checkDeposit_CAccount(int a) {
      CAccount c = null;
      c = new CAccount(001,cBal[a]);
      c.deposit(dCBal[a]);

      String errMsg = String.format("deposit(%f) for CAccount incorrect", dCBal[a]);
      assertEquals(errMsg, cDResult[a], c.getBalance(), ERROR);
   }

   @Test(timeout=1000) public void checkDeposit_CAccount_00() { checkDeposit_CAccount(0); }
   @Test(timeout=1000) public void checkDeposit_CAccount_01() { checkDeposit_CAccount(1); }
   @Test(timeout=1000) public void checkDeposit_CAccount_02() { checkDeposit_CAccount(2); }
   @Test(timeout=1000) public void checkDeposit_CAccount_03() { checkDeposit_CAccount(3); }
   @Test(timeout=1000) public void checkDeposit_CAccount_04() { checkDeposit_CAccount(4); }
   @Test(timeout=1000) public void checkDeposit_CAccount_05() { checkDeposit_CAccount(5); }


   void checkWithdraw_CAccount(int a) {
      CAccount c = null;
      c = new CAccount(001,cBal[a]);
      c.withdraw(wCBal[a]);

      String errMsg = String.format("withdraw(%f) for CAccount incorrect", cBal[a]);
      assertEquals(errMsg, cWResult[a], c.getBalance(), ERROR);
   }

   @Test(timeout=1000) public void checkWithdraw_CAccount_00() { checkWithdraw_CAccount(0); }
   @Test(timeout=1000) public void checkWithdraw_CAccount_01() { checkWithdraw_CAccount(1); }
   @Test(timeout=1000) public void checkWithdraw_CAccount_02() { checkWithdraw_CAccount(2); }
   @Test(timeout=1000) public void checkWithdraw_CAccount_03() { checkWithdraw_CAccount(3); }
   @Test(timeout=1000) public void checkWithdraw_CAccount_04() { checkWithdraw_CAccount(4); }
   @Test(timeout=1000) public void checkWithdraw_CAccount_05() { checkWithdraw_CAccount(5); }


   void checkGetBalance_CAccount(int a) {
      CAccount c = null;
      c = new CAccount(001,cBal[a]);
      // c.withdraw(wCBal[a]);

      String errMsg = String.format("getBalance() for CAccount incorrect");
      assertEquals(errMsg, balResult[a], c.getBalance(), ERROR);
   }

   @Test(timeout=1000) public void checkGetBalance_CAccount_00() { checkGetBalance_CAccount(0); }
   @Test(timeout=1000) public void checkGetBalance_CAccount_01() { checkGetBalance_CAccount(1); }
   @Test(timeout=1000) public void checkGetBalance_CAccount_02() { checkGetBalance_CAccount(2); }
   @Test(timeout=1000) public void checkGetBalance_CAccount_03() { checkGetBalance_CAccount(3); }
   @Test(timeout=1000) public void checkGetBalance_CAccount_04() { checkGetBalance_CAccount(4); }
   @Test(timeout=1000) public void checkGetBalance_CAccount_05() { checkGetBalance_CAccount(5); }


   void checkSetBalance_CAccount(int a) {
      CAccount c = null;
      c = new CAccount();
      c.setBalance(sBal[a]);

      String errMsg = String.format("setBalance(%f) for CAccount incorrect", sBal[a]);
      assertEquals(errMsg, balResult[a], c.getBalance(), ERROR);
   }
   @Test(timeout=1000) public void checkSetBalance_CAccount_00() { checkSetBalance_CAccount(0); }
   @Test(timeout=1000) public void checkSetBalance_CAccount_01() { checkSetBalance_CAccount(1); }
   @Test(timeout=1000) public void checkSetBalance_CAccount_02() { checkSetBalance_CAccount(2); }
   @Test(timeout=1000) public void checkSetBalance_CAccount_03() { checkSetBalance_CAccount(3); }
   @Test(timeout=1000) public void checkSetBalance_CAccount_04() { checkSetBalance_CAccount(4); }
   @Test(timeout=1000) public void checkSetBalance_CAccount_05() { checkSetBalance_CAccount(5); }


   @Test(timeout=1000) public void checkSetGetInterestRate_CAccount_00() {

      String errMsg = String.format("getInterestRate() for CAccount incorrect");
      assertEquals(errMsg, 0.05, CAccount.getInterestRate(), ERROR);
      CAccount.setInterestRate(0.02);
      String errMsg2 = String.format("setInterestRate() for CAccount incorrect");
      assertEquals(errMsg2, 0.02, CAccount .getInterestRate(), ERROR);
   }
   //*************************************BankATM Tests***************
   double atmR[] = {1050.0, 5956.5356, 269836.35, 4633.88, 0.0, 0.0, 0.0, 473.69};
   double atmBal[] = {1000, 5897.56,256987,4588, 0,-98939,-26.5, 469};
   double bal[] = {1000, 5897.56,256987,4588, 0,-98939,-26.5, 469};
   @Test(timeout=1000) public void BankATM_Constructor_test_01() {

      BankATM sa = new BankATM();
      BankATM sa2 = new BankATM(450);
      assertTrue("Default constructor (BankATM) is incorrect",  sa!=null);
      assertTrue("non-Default constructor (BankATM) is incorrect", sa2!=null);

   }


   @Test(timeout=1000) public void BankATM_ClacInterest_test_00() {
      CAccount c = null;
      SAccount s = null;
      CAccount.setInterestRate(0.05);;
      SAccount.setInterestRate(0.01);;
      ArrayList<Account> ac = new ArrayList<>();
      for (int i = 0; i<atmBal.length;i++)
      {
         if(i%2 ==0)
         {
            c = new CAccount(i, atmBal[i]);
            ac.add(c);
         }
         else {
            s = new SAccount(i, atmBal[i]);
            ac.add(s);
         }
      }
      BankATM atm = new BankATM(ac);
      atm.calcInterest();

      assertEquals("calcInterest(BankATM, SAccount) is incorrect", atmR[1], ((SAccount)ac.get(1)).getBalance(), ERROR);
      assertEquals("calcInterest(BankATM, SAccount) is incorrect", atmR[3], ((SAccount)ac.get(3)).getBalance(), ERROR);
      assertEquals("calcInterest(BankATM, SAccount) is incorrect", atmR[5], ((SAccount)ac.get(5)).getBalance(), ERROR);


      assertEquals("calcInterest(BankATM, CAccount) is incorrect", atmR[0], ((CAccount)ac.get(0)).getBalance(), ERROR);
      assertEquals("calcInterest(BankATM, CAccount) is incorrect", atmR[2], ((CAccount)ac.get(2)).getBalance(), ERROR);
      assertEquals("calcInterest(BankATM, CAccount) is incorrect", atmR[4], ((CAccount)ac.get(4)).getBalance(), ERROR);
   }



   @Test(timeout=1000) public void BankATM_SetGetAccounts_test_00() {
      CAccount c = null;
      SAccount s = null;
      ArrayList<Account> ac = new ArrayList<>();
      for (int i = 0; i<atmBal.length;i++)
      {
         if(i%2 ==0)
         {
            c = new CAccount(i, atmBal[i]);
            ac.add(c);
         }
         else {
            s = new SAccount(i, atmBal[i]);
            ac.add(s);
         }

      }
      BankATM atm = new BankATM();
      atm.setAccount(ac);

      assertEquals("Get/SetAccounts(BankATM) is incorrect", ac, atm.getAccount());

   }

   @Test(timeout=1000) public void BankATM_addAccounts_test_00() {
      CAccount c = null;
      SAccount s = null;
      ArrayList<Account> ac = new ArrayList<>();
      c = new CAccount(0, atmBal[0]);

      s = new SAccount(1, atmBal[1]);

      BankATM atm = new BankATM();
      atm.add(c);
      atm.add(s);

      assertTrue("addAccounts(BankATM) is incorrect", atm.getAccount().contains(c));
      assertTrue("addAccounts(BankATM) is incorrect", atm.getAccount().contains(s));
   }


   @Test(timeout=1000) public void BankATM_removeAccounts_test_00() {
      CAccount c = null;
      SAccount s = null;
      ArrayList<Account> ac = new ArrayList<>();
      c = new CAccount(0, atmBal[0]);

      s = new SAccount(1, atmBal[1]);


      BankATM atm = new BankATM();
      atm.add(c);
      atm.add(s);



      assertTrue("removeAccounts(BankATM) is incorrect", atm.remove(c));
      assertFalse("removeAccounts(BankATM) is incorrect", atm.remove(c));
   }
}



