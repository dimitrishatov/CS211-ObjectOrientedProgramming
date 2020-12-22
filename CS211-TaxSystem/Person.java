public class Person {
   // Keeps track of number of Person objects that
   // have been instantiated
   private static int personCount = 1;

   // Fields
   private int    id;
   private String name;
   private String birthday;
   private String ssn;
   private float  income;

   // Constructor only sets the id to the count of objects
   // everything else done through getters and setters
   public Person() {
      id = personCount++;
   }

   // Sets name
   public boolean setName(String name) {
      // Goes through every character and if a character
      // isn't a letter or space returns false
      for (int i = 0; i < name.length(); i++) {
         if (!Character.isLetter(name.charAt(i)) && !Character.isWhitespace(name.charAt(i))) {
            return false;
         }
      }
      // Assigns name to field and returns true
      this.name = name;
      return true;
   }

   // Sets birthday
   public boolean setBirthday(String birthday) {
      if (!validDate(birthday)) {
         return false;
      }
      this.birthday = birthday;
      return true;
   }

   /**
    * Verifies whether a given string is in the correct format
    * YYYY/MM/DD as specified in the assignment HTML page
    *
    * @param date: user inputted date
    * @return whether the date is valid
    */
   private boolean validDate(String date) {
      // Format requires this specific length so
      // anything other than this length is false
      if (date.length() != 10) {
         return false;
      }

      // Verifies that date has digits where there should be digits
      for (int i = 0; i < date.length(); i++) {
         if (i == 4 || i == 7) { // Skips over non-number characters
            continue;
         }
         if (!Character.isDigit(date.charAt(i))) {
            return false;
         }
      }

      // Verify formatting
      if (date.charAt(4) != '/' || date.charAt(7) != '/') {
         return false;
      }

      return true;
   }

   // Sets SSN
   public boolean setSSN(String ssn) {
      if (!validSSN(ssn)) {
         return false;
      }
      this.ssn = ssn;
      return true;
   }

   // Verifies that a string follows the proper
   // SSN formatting
   private boolean validSSN(String ssn) {
      // String must be this length
      if (ssn.length() != 11) {
         return false;
      }

      // Checks that all values except dashes
      // are digits
      for (int i = 0; i < ssn.length(); i++) {
         if (i == 3 || i == 6) { // Skips over non-number characters
            continue;
         }
         if (!Character.isDigit(ssn.charAt(i))) {
            return false;
         }
      }
      return true;
   }

   // Sets income provided it is not negative
   public boolean setIncome(float income) {
      if (income < 0) {
         return false;
      }
      this.income = income;
      return true;
   }

   public float getIncome() {
      return income;
   }

   public int getId() {
      return id;
   }

   public String toString() {
      return name + " " + maskSSN(ssn) + " " + maskBirthday(birthday);
   }

   // Returns a masked version of ssn
   private String maskSSN(String ssn) {
      if (ssn == null) {
         return "";
      }
      return "xxx-xx-" + ssn.substring(7);
   }

   // Returns a masked version of birthday
   private String maskBirthday(String birthday) {
      if (birthday == null) {
         return "";
      }
      return birthday.substring(0,4) + "/**/**";
   }

   public float deduction(Family family) {
      return 0;
   }
}
