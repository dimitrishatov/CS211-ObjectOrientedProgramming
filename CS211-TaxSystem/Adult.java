public class Adult extends Person {
   private String employer;

   // Sets all fields using superclass
   public Adult(String name, String birthday, String ssn, float income, String employer) {
      super.setName(name);
      super.setBirthday(birthday);
      super.setSSN(ssn);
      super.setIncome(income);

      this.employer = employer;
   }

   // Adds gross income to superclass toString
   public String toString() {
      return super.toString() + " " + this.getIncome();
   }

   // Subtracts split social security and medicare taxes
   // from gross income after checking whether income is
   // above limit
   public float adjustedIncome() {
      float income    = this.getIncome();
      float taxableSS = Math.min(this.getIncome(), Taxation.socialSecurityIncomeLimit); // Limit for SS tax

      float socialSecurityTax = (taxableSS * Taxation.socialSecurityRate) / 2;
      float medicareTax       = (income * Taxation.medicareRate) / 2;

      return income - socialSecurityTax - medicareTax;
   }

   // Calculates amount of tax that would be withheld given an income
   public float taxWithheld() {
      float income   = this.getIncome();
      float withheld = 0;

      // 10% withheld from first 50k
      if (income < 50000) {
         return income * 0.10f;
      }
      else {
         withheld += 50000 * 0.10f;
         income -= 50000;
      }

      // 15% withheld on next 100k
      if (income < 100000) {
         withheld += income * 0.15;
         return withheld;
      }
      else {
         withheld += 100000 * 0.15;
         income -= 100000;
      }

      // 20% withheld onwards
      withheld += income * 0.2f;

      return withheld;
   }

   // Calculates deduction
   public float deduction(Family family) {
      float deduction = 0.0f;

      // Base deduction
      deduction += Taxation.adultBaseExemption;

      // Doubles deduction if single parent family
      if (family.getFilingStatus() == 1 && family.getNumChildren() > 0) {
         deduction *= 2;
      }

      // Checks if adjusted income is above 100k, and if it is, reduces deduction by 0.5% for each $1000 over 100k up to %30.
      if (this.adjustedIncome() > 100000) {
         float incomeOver100k = this.adjustedIncome() - 100000;
         int   thousands      = (int) incomeOver100k / 1000;
         float reduction      = 0.005f * thousands;

         // Reduction can't be more than 30%
         if (reduction > 0.3) {
            reduction = 0.3f;
         }
         deduction -= deduction * reduction;
      }

      // Deduction can't be more than adjusted income
      if (deduction > this.adjustedIncome()) {
         deduction = this.adjustedIncome();
      }

      return deduction;
   }

   public String getEmployer() {
      return employer;
   }
}
