import java.util.*;
import java.util.Random;

/** A switching-based Rock-Paper-Scissors player.
  * 
  * @author RR
  */
  public class SwitchALot implements RoShamBot {

    ArrayList<Action> my_history = new ArrayList<Action>();
    
    /** Returns biased RPSLS. 
     * 
     * @param prob_rock
     * @param prob_paper
     * @param prob_scissors
     * @param prob_lizard
     * @return Action
     */
    Action biased_roshambo (double prob_rock, double prob_paper, double prob_scissors, double prob_lizard) {
        Random rand = new Random();
        double prob = rand.nextDouble();
        if (prob < prob_rock) { return Action.ROCK; }
        else if (prob < prob_rock + prob_paper) { return Action.PAPER; }
        else if (prob < prob_rock + prob_paper + prob_scissors) { return Action.SCISSORS; }
        else if (prob < prob_rock + prob_paper + prob_scissors + prob_lizard) { return Action.LIZARD; }
        else { return Action.SPOCK; }
    }

    /** Switches move frequently, seldom repeats the previous pick.
      * 
      * @param lastOpponentMove the action that was played by the opposing 
      *        agent on the last round.
      *
      * @return the next action to play.
    */
    public Action getNextMove(Action lastOpponentMove) {
        if (my_history.size() == 0) {
            my_history.add(Action.ROCK);
        }

        // Play last move with low probability
        Action myLastMove = my_history.get(my_history.size() - 1); 
        if (myLastMove.equals(Action.ROCK)) {
            return biased_roshambo(0.05, 0.2375, 0.2375, 0.2375);
        } else if (myLastMove.equals(Action.PAPER)) {
            return biased_roshambo(0.2375, 0.05, 0.2375, 0.2375);
        } else if (myLastMove.equals(Action.SCISSORS)) {
            return biased_roshambo(0.2375, 0.2375, 0.05, 0.2375);
        } else if (myLastMove.equals(Action.LIZARD)) {
            return biased_roshambo(0.2375, 0.2375, 0.2375, 0.05);
        } else return biased_roshambo(0.2375, 0.2375, 0.2375, 0.2375);
    }
}