import java.util.*;
import java.util.Random;

/** A frequency-based Rock-Paper-Scissors player.
  * 
  * @author RR
  */
  public class FreqBot implements RoShamBot {

    ArrayList<Action> opp_history = new ArrayList<Action>();
    
    /** Random coin flip.
     * 
     * @return 0 or 1
     */
    public int coinFlip() {
        Random r = new Random();
        int flip = r.nextInt(2);
        if (flip == 1) {
           return 0;
        } else {
           return 1;
        }
     }
    /** Beats the copponent's most frequent action choice.
      * 
      * @param lastOpponentMove the action that was played by the opposing 
      *        agent on the last round.
      *
      * @return the next action to play.
    */
    public Action getNextMove(Action lastOpponentMove) {
        int rock_count, paper_count, scissors_count, lizard_count, spock_count;

        if (opp_history.size() == 0)
            opp_history.add(Action.ROCK);
        else opp_history.add(lastOpponentMove);

        rock_count = 0; paper_count = 0; scissors_count = 0; lizard_count = 0; spock_count = 0;
        for (int i = 0; i <= opp_history.size() - 1; i++){
            if (opp_history.get(i).equals(Action.ROCK)) {
                rock_count++;
            } else if (opp_history.get(i).equals(Action.PAPER)) {
                paper_count++;
            } else if (opp_history.get(i).equals(Action.SCISSORS)) {
                scissors_count++;
            } else if (opp_history.get(i).equals(Action.LIZARD)) {
                lizard_count++;
            } else { 
                spock_count++;
            }
        }

        if (rock_count == Math.max(rock_count, Math.max(paper_count, Math.max(scissors_count, Math.max(lizard_count, spock_count))))) {
            int cf = coinFlip();
            if (cf == 0) {
                return Action.PAPER;
            } else {
                return Action.SPOCK;
            }
        } else if (paper_count == Math.max(rock_count, Math.max(paper_count, Math.max(scissors_count, Math.max(lizard_count, spock_count))))) {
            int cf = coinFlip();
            if (cf == 0) {
                return Action.SCISSORS;
            } else {
                return Action.LIZARD;
            }
        } else if (scissors_count == Math.max(rock_count, Math.max(paper_count, Math.max(scissors_count, Math.max(lizard_count, spock_count))))) {
            int cf = coinFlip();
            if (cf == 0) {
                return Action.ROCK;
            } else {
                return Action.SPOCK;
            }
        } else if (lizard_count == Math.max(rock_count, Math.max(paper_count, Math.max(scissors_count, Math.max(lizard_count, spock_count))))) {
            int cf = coinFlip();
            if (cf == 0) {
                return Action.SCISSORS;
            } else {
                return Action.ROCK;
            }
        } else {
            int cf = coinFlip();
            if (cf == 0) {
                return Action.PAPER;
            } else {
                return Action.LIZARD;
            }
        }
    }
}