/** A copying-based Rock-Paper-Scissors player.
  * 
  * @author Lindy Bustabad and Abby Santiago
  */
public class CopyBot implements RoShamBot {
 
    /** Play the action that would have beat the opponent last turn.
      * 
      * @param lastOpponentMove the action that was played by the opponent on
      *        the last round (this is disregarded).
      * @return the next action to play.
      */
    public Action getNextMove(Action lastOpponentMove) {

        if(lastOpponentMove ==  Action.ROCK){
            return Action.PAPER;
        }
        else if(lastOpponentMove ==  Action.PAPER){
            return Action.SCISSORS;
        }
        else if(lastOpponentMove ==  Action.SCISSORS){
            return Action.SPOCK;
        }
        else if(lastOpponentMove ==  Action.LIZARD){
            return Action.ROCK;
        }
        else{
            return Action.LIZARD;
        }
    }
    
}