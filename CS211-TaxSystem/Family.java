public class Family {
   private int      numMembers;
   private int      filingStatus;
   private Person[] familyMembers;

   private float    calculatedTax;

   public Family(int numMembers, int filingStatus) {
      this.numMembers   = numMembers;
      this.filingStatus = filingStatus;

      familyMembers = new Person[numMembers];
   }

   public Person[] getFamilyMembers() {
      return familyMembers;
   }

   // Adds person to first space in familyMembers array
   // which is empty / null. It is assumed that no resizing
   // is needed, as numMembers was declared explicitly.
   public void addMember(Person person) {
      for (int i = 0; i < familyMembers.length; i++) {
         if (familyMembers[i] == null) {
            familyMembers[i] = person;
            break;
         }
      }
   }

   // Iterates through family members and counts how
   // many are adults
   public int getNumAdults() {
      int count = 0;
      for (Person familyMember : familyMembers) {
         if (familyMember instanceof Adult) {
            count++;
         }
      }
      return count;
   }

   // Iterates through family members and counts how
   // many are children
   public int getNumChildren() {
      int count = 0;
      for (Person familyMember : familyMembers) {
         if (familyMember instanceof Child) {
            count++;
         }
      }
      return count;
   }

   public int getFilingStatus() {
      return filingStatus;
   }

   // Iterates through family members, if member
   // is an adult then taxable income is added to total.
   // Once done iterating the total taxable income is returned.
   public float getTaxableIncome() {
      float taxableIncome = 0;
      for (Person familyMember : familyMembers) {
         if (familyMember instanceof Adult) {
            taxableIncome += ((Adult) familyMember).adjustedIncome();
            taxableIncome -= familyMember.deduction(this);
         }
      }
      return taxableIncome;
   }


   public float taxCredit() {
      // Checks whether tax income is in low 50% of median income per capita
      if (!(this.getTaxableIncome() < (Taxation.medianIncomePerCapita / 2))) {
         return 0f;
      }

      // Credit is 30 per each 1000 of taxable Income
      float credit = ((int) this.getTaxableIncome() / 1000) * 30;

      // Children are eligible for additional credit which is
      // equal to tuition or $1000, whichever is lower
      for (Person familyMember : familyMembers) {
         if (familyMember instanceof Child) {
            Child child = (Child) familyMember;
            if (this.filingStatus == 1 || this.filingStatus == 2) {
               credit += Math.min(child.getTuition(), 1000);
            }
            else {
               credit += (Math.min(child.getTuition(), 1000)) / 2;
            }
         }
      }

      // Maximum credit per family is 2000
      if (credit > 2000) {
         credit = 2000;
      }

      // Maximum credit per family is 2000 or amount of pre-credit tax
      float tax = this.calculatePreCreditTax();
      return Math.min(tax, credit);
   }

   // Returns the amount of tax a family either owes or is to be refunded with
   public float calculateTax() {
      float tax = this.calculatePreCreditTax();

      // Subtracts tax withheld during payroll for each adult
      for (Person familyMember: familyMembers) {
         if (familyMember instanceof Adult) {
            Adult adult = (Adult) familyMember;
            tax -= adult.taxWithheld();
         }
      }

      // Subtracts tax credit
      tax -= this.taxCredit();

      return tax;
   }

   public float calculatePreCreditTax() {
      float tax               = 0f;
      int   maximumTaxBracket = Taxation.maxIncomeTaxBracket(this);

      // Sums the amounts of tax that correspond to each bracket up to the max bracket
      while (maximumTaxBracket > 0) {
         tax += Taxation.bracketIncome(this, maximumTaxBracket) * Taxation.taxRates[maximumTaxBracket - 1][this.filingStatus - 1];
         maximumTaxBracket--;
      }

      return tax;
   }
}
