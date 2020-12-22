public class Analytics {
   private float povertyThreshold = 26200;
   private TaxYear taxYear;

   public Analytics(TaxYear taxyear) {
      this.taxYear = taxyear;
   }

   // Sets poverty threshold
   public void setPovertyThreshold(float povertyThreshold) {
      this.povertyThreshold = povertyThreshold;
   }

   // Returns average taxable income per family for given year
   public float averageFamilyIncome() {
      float totalIncome = 0;
      for (int i = 0; i < taxYear.numberOfReturnsFiled(); i++) {
         totalIncome += taxYear.getTaxReturn(i).getTaxableIncome();
      }
      return totalIncome / taxYear.numberOfReturnsFiled();
   }

   // Finds average taxable income
   public float averagePersonIncome() {
      float totalIncome = 0;
      int   personCount = 0;

      for (int i = 0; i < taxYear.numberOfReturnsFiled(); i++) {
        totalIncome += taxYear.getTaxReturn(i).getTaxableIncome();
        personCount += taxYear.getTaxReturn(i).getFamilyMembers().length;
      }
      return totalIncome / personCount;
   }

   // Goes through every person in every family and finds
   // the maximum taxable / adjusted income
   public float maxPersonIncome() {
      float maxIncome = 0f;
      for (int i = 0; i < taxYear.numberOfReturnsFiled(); i++) {
         Person[] people = taxYear.getTaxReturn(i).getFamilyMembers();

         // For every individual in family that is an adult check taxableIncome > maxIncome
         for (Person person : people) {
            if (person instanceof Adult) {
               Adult adult = (Adult) person;
               // Individual taxable income is adjusted income - deduction
               if (adult.adjustedIncome() - adult.deduction(taxYear.getTaxReturn(i)) > maxIncome) {
                  maxIncome = adult.adjustedIncome();
               }
            }
         }
      }
      return maxIncome;
   }

   // Goes through every family and finds / returns
   // the highest taxable income
   public float maxFamilyIncome() {
      float maxIncome = 0f;
      for (int i = 0; i < taxYear.numberOfReturnsFiled(); i++) {
         if (taxYear.getTaxReturn(i).getTaxableIncome() > maxIncome) {
            maxIncome = taxYear.getTaxReturn(i).getTaxableIncome();
         }
      }
      return maxIncome;
   }

   // Counts up families with taxable income below poverty limit
   // and returns the amount
   public int familiesBelowPovertyLimit() {
      int familyBelowPovertyLimitCount = 0;
      for (int i = 0; i < taxYear.numberOfReturnsFiled(); i++) {
         if (taxYear.getTaxReturn(i).getTaxableIncome() < povertyThreshold) {
            familyBelowPovertyLimitCount++;
         }
      }
      return familyBelowPovertyLimitCount;
   }

   public int familyRank(Family family) {
      int rankTracker = 1;
      for (int i = 0; i < taxYear.numberOfReturnsFiled(); i++) {
         // Since no two families have the exact same income, if family has same income
         // it must be the same family so we skip
         if (taxYear.getTaxReturn(i).getTaxableIncome() == family.getTaxableIncome()) {
            continue;
         }

         // Family drops a rank for every family with a higher taxable income for the year
         if (family.getTaxableIncome() < taxYear.getTaxReturn(i).getTaxableIncome()) {
            rankTracker++;
         }
      }
      return rankTracker;
   }
}
