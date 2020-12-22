public class WeightLifting extends Anaerobic {
   /**
    * Returns muscles targeted
    * @return Muscle[] of muscles targeted
    */
   @Override
   public Muscle[] muscleTargeted() {
      return new Muscle[] {Muscle.Shoulders, Muscle.Legs, Muscle.Arms, Muscle.Triceps};
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
      double[] intensities = {6.0, 5.0, 3.5};
      return calculateCalorieLoss(intensity, intensities, weight, duration);
   }

   /**
    * Returns description of exercise
    * @return name of class
    */
   @Override
   public String description() {
      return "WeightLifting";
   }
}
