public abstract class Anaerobic implements Fitness {
   /**
    * Returns muscles targeted
    * @return Muscle[] of muscles targeted
    */
   @Override
   public abstract Muscle[] muscleTargeted();

   /**
    * Calculates calories lost based on MET
    *
    * @param intensity intensity of exercise
    * @param weight weight of user
    * @param duration duration of exercise in minutes
    * @return calories lost
    */
   @Override
   public abstract double calorieLoss(Intensity intensity, double weight, int duration);

   /**
    * Calculations / Helper method for calorie loss
    *
    * @param intensity intensity of exercise
    * @param intensitiesFromHighToLow double array of intensities in order (high, medium, low)
    * @param weight weight of user
    * @param duration duration of exercise in minutes
    * @return calories lost
    */
   protected double calculateCalorieLoss(Intensity intensity, double[] intensitiesFromHighToLow, double weight, int duration) {
      double durationInHours = ((double) duration) / 60.0;

      switch (intensity) {
         case HIGH:
            return intensitiesFromHighToLow[0] * weight * durationInHours;
         case MEDIUM:
            return intensitiesFromHighToLow[1] * weight * durationInHours;
         case LOW:
            return intensitiesFromHighToLow[2] * weight * durationInHours;
         default:
            return 0.0;
      }
   }

   /**
    * Returns description of exercise
    * @return String with description
    */
   public String description() {
      return "Aerobic means \"without oxygen\"";
   }
}
