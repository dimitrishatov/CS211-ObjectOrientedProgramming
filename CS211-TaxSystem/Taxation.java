public class Taxation {
   public static final float socialSecurityRate        = 0.124f;
   public static final float socialSecurityIncomeLimit = 137700;
   public static final float medicareRate              = 0.029f;
   public static final float adultBaseExemption        = 3000;
   public static final float childBaseExemption        = 2000;
   public static final float medianIncomePerCapita     = 31099;

   private static final float[][] incomeBrackets = new float[][] {
           {0f        , 0f        , 0f        },
           {10000.01f , 20000.01f , 12000.01f },
           {40000.01f , 70000.01f , 44000.01f },
           {80000.01f , 160000.01f, 88000.01f },
           {160000.01f, 310000.01f, 170000.01f}
   };

   public static final float[][] taxRates = new float[][] {
           {0.10f, 0.10f, 0.10f},
           {0.12f, 0.12f, 0.12f},
           {0.22f, 0.23f, 0.24f},
           {0.24f, 0.25f, 0.26f},
           {0.32f, 0.33f, 0.35f}
   };

   public static int getNumTaxBrackets() {
      return incomeBrackets.length;
   }

   // Goes through incomeBrackets table and adds 1 to counter
   // for each tax bracket that is less than a families
   // taxable income then returns the bracket
   public static int maxIncomeTaxBracket(Family family) {
      int   filingStatus  = family.getFilingStatus();
      float taxableIncome = family.getTaxableIncome();

      int maxTaxBracket = 0;
      for (int i = 0; i < taxRates.length; i++) {
         if (taxableIncome > incomeBrackets[i][filingStatus - 1]) {
            maxTaxBracket += 1;
         }
         else {
            break;
         }
      }
      return maxTaxBracket;
   }

   // Same as above but income and filingStatus explicitly provided
   public static int maxIncomeTaxBracket(float taxableIncome, int filingStatus) {
      int maxTaxBracket = 0;
      for (int i = 0; i < taxRates.length; i++) {
         if (taxableIncome > incomeBrackets[i][filingStatus - 1]) {
            maxTaxBracket += 1;
         }
         else {
            break;
         }
      }
      return maxTaxBracket;
   }

   // Returns the portion of a family's taxable income that falls within the bracket b
   public static float bracketIncome(Family family, int bracket) {
      int   filingStatus  = family.getFilingStatus();
      float taxableIncome = family.getTaxableIncome();

      // If a family does not have as much money as the minimum required to be
      // in a bracket, they have 0 taxable income in that bracket
      if (taxableIncome < incomeBrackets[bracket - 1][filingStatus - 1]) {
         return 0f;
      }

      // Need to check if bracket == 5 since some operations depend on knowing
      // the minimum in the next higher-up bracket
      if (bracket != 5) {

         // If income is greater than the next bracket up the taxable income
         // is equal to the range of income in that bracket
         if (taxableIncome > incomeBrackets[bracket][filingStatus - 1]) {
            return incomeBrackets[bracket][filingStatus - 1] - incomeBrackets[bracket - 1][filingStatus - 1];
         }

         // If a family's taxable income falls within the appropriate range,
         // subtract the bottom of the range from the income and you're left with
         // the taxable income for that range
         if (taxableIncome > incomeBrackets[bracket - 1][filingStatus - 1] && taxableIncome < incomeBrackets[bracket][filingStatus - 1]) {
            return taxableIncome - incomeBrackets[bracket - 1][filingStatus -1];
         }
      }
      // If it enters this else statement then it is checking for the 5th tax bracket.
      else {
         // If we get to this point then taxable income is greater than the min value, so we can
         // simply subtract the min value, and all that's left falls within this range.
         return taxableIncome - incomeBrackets[bracket - 1][filingStatus -1];
      }
      return 0f;
   }

   // This method functions the exact same as the above except given income,
   // filingStatus, and bracket explicitly.
   public static float bracketIncome(float taxableIncome, int filingStatus, int bracket) {
      if (taxableIncome < incomeBrackets[bracket - 1][filingStatus - 1]) {
         return 0f;
      }
      if (bracket != 5) {
         if (taxableIncome > incomeBrackets[bracket][filingStatus - 1]) {
            return incomeBrackets[bracket][filingStatus - 1] - incomeBrackets[bracket - 1][filingStatus - 1];
         }
         if (taxableIncome > incomeBrackets[bracket - 1][filingStatus - 1] && taxableIncome < incomeBrackets[bracket][filingStatus - 1]) {
            return taxableIncome - incomeBrackets[bracket - 1][filingStatus -1];
         }
      }
      else {
         return taxableIncome - incomeBrackets[bracket - 1][filingStatus -1];
      }
      return 0f;
   }

   // Simple table query
   public static float bracketTaxRate(int bracket, int filingStatus) {
      return taxRates[bracket - 1][filingStatus - 1];
   }
}
