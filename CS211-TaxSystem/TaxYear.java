public class TaxYear {
   private Family[] taxesFiled;
   private int taxFileCount = 0;

   public TaxYear(int max) {
      taxesFiled = new Family[max];
   }

   // Files family tax return for the year and validates submitted data,
   // returns false if any discrepancies in filing status. If all checks out,
   // adds family to taxesFiled
   public boolean taxFiling(Family family) {
      // Family is not single
      if (family.getFilingStatus() == 1 && family.getNumAdults() > 1) {
         return false;
      }

      // Family has no adults
      if (family.getNumAdults() == 0) {
         return false;
      }

      // Passes all checks, is added to array
      taxesFiled[taxFileCount++] = family;
      return true;
   }

   // Returns withheld tax of all adults across all
   // families
   public float taxWithheld() {
      float taxWithheld = 0f;
      for (int i = 0; i < taxFileCount; i++) {
         // Array of all family members
         Person[] family = taxesFiled[i].getFamilyMembers();

         // Every adult adds withheld tax to counter variable
         for (Person person : family) {
            if (person instanceof Adult) {
               Adult adult = (Adult) person;
               taxWithheld += adult.taxWithheld();
            }
         }
      }
      return taxWithheld;
   }

   // Returns total tax owed by all families
   // based on taxable income only
   public float taxOwed() {
      float taxOwed = 0f;
      for (int i = 0; i < taxFileCount; i++) {
         // We get all the people in a family into an array
         Person[] peopleInFamily = taxesFiled[i].getFamilyMembers();

         // We sum up all income (no credits, deductions, etc)
         float incomeSum = 0f;
         for (Person person : peopleInFamily) {
            if (person instanceof Adult) {
               Adult adult = (Adult) person;
               incomeSum += adult.adjustedIncome();
            }
         }

         // We add up the tax owed on the sum of adjusted income and
         // add this to our taxOwed counter
         int filingStatus      = taxesFiled[i].getFilingStatus();
         int maximumTaxBracket = Taxation.maxIncomeTaxBracket(incomeSum, filingStatus);

         while (maximumTaxBracket > 0) {
            taxOwed += Taxation.bracketIncome(incomeSum, filingStatus, maximumTaxBracket) * Taxation.taxRates[maximumTaxBracket - 1][filingStatus - 1];
            maximumTaxBracket--;
         }
      }
      return taxOwed;
   }

   // Returns sum of calculateTax for all families
   public float taxDue() {
      float taxDue = 0f;
      for (int i = 0; i < taxFileCount; i++) {
         taxDue += taxesFiled[i].calculateTax();
      }
      return taxDue;
   }

   public float taxCredits() {
      int creditCount = 0;
      for (int i = 0; i < taxFileCount; i++) {
         if (taxesFiled[i].taxCredit() != 0) {
            creditCount++;
         }
      }
      return creditCount;
   }

   // Returns number of returns in taxesFiled
   public int numberOfReturnsFiled() {
      return taxFileCount;
   }

   // Goes through each family and adds up number of total
   // adults.
   public int numberOfPersonsFiled() {
      int personCount = 0;
      for (int i = 0; i < taxFileCount; i++) {
         personCount += taxesFiled[i].getNumAdults() + taxesFiled[i].getNumChildren();
      }
      return personCount;
   }

   public Family getTaxReturn(int index) {
      return taxesFiled[index];
   }

}
