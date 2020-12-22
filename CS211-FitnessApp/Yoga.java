public class Yoga extends Flexibility {
   /**
    * Returns muscles targeted
    * @return Muscle[] of muscles targeted
    */
   @Override
   public Muscle[] muscleTargeted() {
      return new Muscle[] {Muscle.Triceps, Muscle.Biceps};
   }

   /**
    * Calculates calories lost based on MET.
    *
    * @param intensity Intensity of exercise
    * @param weight weight of user
    * @param duration duration of exercise in minutes
    * @return calories lost
    */
   @Override
   public double calorieLoss(Intensity intensity, double weight, int duration) {
      double[] intensities = {4.0, 3.0, 2.0};
      return calculateCalorieLoss(intensity, intensities, weight, duration);
   }

   /**
    * Returns description of exercise
    * @return name of class
    */
   @Override
   public String description() {
      return "Yoga";
   }
}
