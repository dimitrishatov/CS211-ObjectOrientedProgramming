import java.util.ArrayList;
import java.util.Collection;

public class MazeSolver {
   private Maze maze;
   private ArrayList<String> solution = new ArrayList<String>();

   public MazeSolver(Maze maze) {
      this.maze = maze;
   }

   public ArrayList<String> solve(MazePosition position, Collection<MazePosition> visited) {
      // If my current position is the goal, I'm done and I've found my goal. Return ArrayList with just "Goal!"
      if (maze.isGoal(position)) {
         ArrayList<String> result = new ArrayList<String>();
         result.add("Goal!");
         return result;
      }

      // If my current position is blocked (not clear), or I've already visited this position
      // previously, then there is no obstacle free path from here to the goal so I'm done.
      if (!maze.isClear(position) || visited.contains(position)) {
         return null;
      }

      // We have now visited this position and will carry out or checks
      visited.add(position);

      // We are going to check if the goal is in our immediate vicinity before taking any further steps
      if (maze.isGoal(position.up())) {
         solution.add("Up");
         solution.add("Goal!");
         return solution;
      }
      if (maze.isGoal(position.left())) {
         solution.add("Left");
         solution.add("Goal!");
         return solution;
      }
      if (maze.isGoal(position.down())) {
         solution.add("Down");
         solution.add("Goal!");
         return solution;
      }
      if (maze.isGoal(position.right())) {
         solution.add("Right");
         solution.add("Goal!");
         return solution;
      }

      // We have not found our goal yet so we will move in the next open and unvisited direction
      if (maze.isClear(position.up()) && !visited.contains(position.up())) {
         solution.add("Up");
         return solve(position.up(), visited);
      }
      if (maze.isClear(position.left()) && !visited.contains(position.left())) {
         solution.add("Left");
         return solve(position.left(), visited);
      }
      if (maze.isClear(position.down()) && !visited.contains(position.down())) {
         solution.add("Down");
         return solve(position.down(), visited);
      }
      if (maze.isClear(position.right()) && !visited.contains(position.right())) {
         solution.add("Right");
         return solve(position.right(), visited);
      }

      // If we get all the way down here it means we have not found our goal and there
      // are no clear spaces to go to from the current position. We are going to go back
      // through our visited spaces until we find a visited space that can branch off
      // onto an unvisited space. The directions will also be removed from the solution
      // as we backtrack, removing directions which we have now found to lead nowhere.
      // We do not remove the incorrectly visited steps from visited spaces in order to
      // prevent the algorithm from following the same path again.
      while (true) {
         switch (solution.remove(solution.size() - 1)) {
            // For each case here we go back a step and try taking a step in a different
            // direction. If no direction works we just keep going back more steps until
            // we are able to start going a different way.
            case "Up":
               position = position.down();
               if (maze.isClear(position.left()) && !visited.contains(position.left())) {
                  solution.add("Left");
                  return solve(position.left(), visited);
               }
               if (maze.isClear(position.down()) && !visited.contains(position.down())) {
                  solution.add("Down");
                  return solve(position.down(), visited);
               }
               if (maze.isClear(position.right()) && !visited.contains(position.right())) {
                  solution.add("Right");
                  return solve(position.right(), visited);
               }
               break;
            case "Left":
               position = position.right();
               if (maze.isClear(position.up()) && !visited.contains(position.up())) {
                  solution.add("Up");
                  return solve(position.up(), visited);
               }
               if (maze.isClear(position.down()) && !visited.contains(position.down())) {
                  solution.add("Down");
                  return solve(position.down(), visited);
               }
               if (maze.isClear(position.right()) && !visited.contains(position.right())) {
                  solution.add("Right");
                  return solve(position.right(), visited);
               }
               break;
            case "Down":
               position = position.up();
               if (maze.isClear(position.up()) && !visited.contains(position.up())) {
                  solution.add("Up");
                  return solve(position.up(), visited);
               }
               if (maze.isClear(position.left()) && !visited.contains(position.left())) {
                  solution.add("Left");
                  return solve(position.left(), visited);
               }
               if (maze.isClear(position.right()) && !visited.contains(position.right())) {
                  solution.add("Right");
                  return solve(position.right(), visited);
               }
               break;
            case "Right":
               position = position.left();
               if (maze.isClear(position.up()) && !visited.contains(position.up())) {
                  solution.add("Up");
                  return solve(position.up(), visited);
               }
               if (maze.isClear(position.left()) && !visited.contains(position.left())) {
                  solution.add("Left");
                  return solve(position.left(), visited);
               }
               if (maze.isClear(position.down()) && !visited.contains(position.down())) {
                  solution.add("Down");
                  return solve(position.down(), visited);
               }
               break;
            default:
               return null;
         }
      }
   }
}
