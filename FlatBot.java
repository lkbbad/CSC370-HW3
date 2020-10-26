import java.util.*;
import java.util.Random;

/** A flat distribution-based Rock-Paper-Scissors player.
  * 
  * @author RR
  */
  public class FlatBot implements RoShamBot {

    ArrayList<Action> my_history = new ArrayList<Action>();
    static int rock_count, paper_count, scissors_count, lizard_count, spock_count;
    Action myLastMove;
    Action choice;

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

    /** Flat distribution: 20% chance of most frequent actions, 80% chance of choosing the least frequent actions.
      * 
      * @param lastOpponentMove the action that was played by the opposing 
      *        agent on the last round.
      *
      * @return the next action to play.
    */
    public Action getNextMove(Action lastOpponentMove) {
        if (my_history.size() == 0) {
            my_history.add(Action.ROCK);
            rock_count = 1; paper_count = 0; scissors_count = 0; lizard_count = 0; spock_count = 0;
        } else {
            myLastMove = my_history.get(my_history.size() - 1);
            if (myLastMove.equals(Action.ROCK)){
                rock_count++;
            } else if (myLastMove.equals(Action.PAPER)){
                paper_count++;
            } else if (myLastMove.equals(Action.SCISSORS)){
                scissors_count++;
            } else if (myLastMove.equals(Action.LIZARD)){
                lizard_count++;
            } else { spock_count++; }
        }

        if (rock_count == Math.min(rock_count, Math.min(paper_count, Math.min(scissors_count, Math.min(lizard_count, spock_count))))) {
            choice = biased_roshambo(0.8, 0.05, 0.05, 0.05);
        } else if (paper_count == Math.min(rock_count, Math.min(paper_count, Math.min(scissors_count, Math.min(lizard_count, spock_count))))) {
            choice = biased_roshambo(0.05, 0.8, 0.05, 0.05);
        } else if (scissors_count == Math.min(rock_count, Math.min(paper_count, Math.min(scissors_count, Math.min(lizard_count, spock_count))))) {
            choice = biased_roshambo(0.05, 0.05, 0.8, 0.05);
        } else if (lizard_count == Math.min(rock_count, Math.min(paper_count, Math.min(scissors_count, Math.min(lizard_count, spock_count))))) {
            choice = biased_roshambo(0.05, 0.05, 0.05, 0.8);
        } else {
            choice = biased_roshambo(0.05, 0.05, 0.05, 0.05);
        }
        return choice;
    }
}