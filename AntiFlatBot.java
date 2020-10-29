import java.util.*;
import java.util.Random;

/** A flat distribution-based Rock-Paper-Scissors player.
  * 
  * @author Lindy Bustabad and Abby Santiago
  */
  public class AntiFlatBot implements RoShamBot {

    ArrayList<Action> opp_history = new ArrayList<Action>();
    static int rock_count, paper_count, scissors_count, lizard_count, spock_count;
    Action opp_lastMove;
    Action choice;

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

    /** Maximally exploit flat distribution.
      * 
      * @param lastOpponentMove the action that was played by the opposing 
      *        agent on the last round.
      *
      * @return the next action to play.
    */
    public Action getNextMove(Action lastOpponentMove) {
        if (opp_history.size() == 0) {
            opp_history.add(Action.ROCK);
            rock_count = 1; paper_count = 0; scissors_count = 0; lizard_count = 0; spock_count = 0;
        } else {
            opp_lastMove = opp_history.get(opp_history.size() - 1);
            if (opp_lastMove.equals(Action.ROCK)){
                rock_count++;
            } else if (opp_lastMove.equals(Action.PAPER)){
                paper_count++;
            } else if (opp_lastMove.equals(Action.SCISSORS)){
                scissors_count++;
            } else if (opp_lastMove.equals(Action.LIZARD)){
                lizard_count++;
            } else { spock_count++; }
        }

        int cf;
        if (rock_count == Math.min(rock_count, Math.min(paper_count, Math.min(scissors_count, Math.min(lizard_count, spock_count))))) {
            cf = coinFlip();
            if (cf == 0) {
                choice = Action.PAPER;
            } else {
                choice = Action.SPOCK;
            }
        } else if (paper_count == Math.min(rock_count, Math.min(paper_count, Math.min(scissors_count, Math.min(lizard_count, spock_count))))) {
            cf = coinFlip();
            if (cf == 0) {
                choice = Action.SCISSORS;
            } else {
                choice = Action.LIZARD;
            }
        } else if (scissors_count == Math.min(rock_count, Math.min(paper_count, Math.min(scissors_count, Math.min(lizard_count, spock_count))))) {
            cf = coinFlip();
            if (cf == 0) {
                choice = Action.ROCK;
            } else {
                choice = Action.SPOCK;
            }
        } else if (lizard_count == Math.min(rock_count, Math.min(paper_count, Math.min(scissors_count, Math.min(lizard_count, spock_count))))) {
            cf = coinFlip();
            if (cf == 0) {
                choice = Action.SCISSORS;
            } else {
                choice = Action.ROCK;
            }
        } else {
            cf = coinFlip();
            if (cf == 0) {
                choice = Action.LIZARD;
            } else {
                choice = Action.PAPER;
            }
        }
        return choice;
    }
}