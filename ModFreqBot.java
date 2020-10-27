import java.util.*;
import java.util.Random;

/** A frequency-based Rock-Paper-Scissors player based off the modified freq bot in the paper.
  * 
  * 
  */
  public class ModFreqBot implements RoShamBot {

    ArrayList<Action> opp_history = new ArrayList<Action>();
  
    //float for math purposes
    float numR, numP, numS, numL = 0;

   
    float probR, probP, probS, probL = 1/5;


    float predictionR , predictionP, predictionS, predictionL = 0;

   
    //see: results of paper playing against R-P-S 20-20-60 
    int targetPredictionSize = 225;

    int remainingPredSize = 0;
    
    /** Beats the copponent's most frequent action choice.
      * 
      * @param lastOpponentMove the action that was played by the opposing 
      *        agent on the last round.
      *
      * @return the next action to play.
    */
    public Action getNextMove(Action lastOpponentMove) {
        
        
      
        if (opp_history.size() == 0){
            opp_history.add(Action.ROCK);
            initializeGame();
    
        }
        else{
            opp_history.add(lastOpponentMove);
        } 

       
        Action prediction = predictor(lastOpponentMove);

        //play move that beats prediction
        if(prediction ==  Action.ROCK){
            return Action.PAPER;
        }
        else if(prediction ==  Action.PAPER){
            return Action.SCISSORS;
        }
        else if(prediction ==  Action.SCISSORS){
            return Action.SPOCK;
        }
        else if(prediction ==  Action.LIZARD){
            return Action.ROCK;
        }
        else{
            return Action.LIZARD;
        }

    }

    private void initializeGame(){
      
        predictionR = probR * targetPredictionSize;
        predictionP = probP * targetPredictionSize;
        predictionS = probS * targetPredictionSize;
        predictionL = probL * targetPredictionSize;
        

        remainingPredSize = targetPredictionSize;
    }

    private Action predictor(Action lastOpponentMove){

        //increment freq of moves of opponent

        if(lastOpponentMove == Action.ROCK){
            numR++;
        }
        else if(lastOpponentMove == Action.PAPER){
            numP++;
        }
        else if(lastOpponentMove == Action.SCISSORS){
            numS++;
        }
        else if(lastOpponentMove == Action.LIZARD){
            numL++;
        }
      


        if(remainingPredSize <= 0){
  
            recomputeFutureProb();
        }

        //calculate the probabilities of each move given we are on the current turn
        float probRTurn, probPTurn, probSTurn, probLTurn = 0;
       
        probRTurn = (predictionR - numR)/remainingPredSize;
        probPTurn = (predictionP - numP)/remainingPredSize;
        probSTurn = (predictionS - numS)/remainingPredSize;
        probLTurn = (predictionL - numL)/remainingPredSize;
     
        //if the probablities are not valid recompute state of game and then recompute the current probabilites
        if((probRTurn < 0.0) || (probPTurn < 0.0) || (probSTurn < 0.0) || (probLTurn < 0.0)  || (probRTurn + probPTurn + probSTurn + probLTurn > 1.0)){
            recomputeFutureProb();
            probRTurn = (predictionR - numR)/remainingPredSize;
            probPTurn = (predictionP - numP)/remainingPredSize;
            probSTurn = (predictionS - numS)/remainingPredSize;
            probLTurn = (predictionL - numL)/remainingPredSize;
        }
  
        remainingPredSize--;
      

        return chooseBiasMove(probRTurn, probPTurn, probSTurn, probLTurn);
    }


    //reinitialize the game because our remaining size hit 0
    private void recomputeFutureProb(){
        //recompute probabilities
        probR = numR/opp_history.size();
        probP = numP/opp_history.size();
        probS = numS/opp_history.size();
        probL = numL/opp_history.size();
       
        //recompute current prediction 
        predictionR = probR * (opp_history.size() + targetPredictionSize);
        predictionP = probP * (opp_history.size() + targetPredictionSize);
        predictionS = probS * (opp_history.size() + targetPredictionSize);
        predictionL = probL * (opp_history.size() + targetPredictionSize);
       

        remainingPredSize = targetPredictionSize;
    }


    private Action chooseBiasMove(float probRTurn, float probPTurn, float probSTurn, float probLTurn){
        double turn = Math.random();
    
        turn = (float) turn;
   
        
        if(turn < probRTurn){
            return Action.ROCK;
        }
        else if(turn < probRTurn + probPTurn){
            return Action.PAPER;
        }
        else if(turn < probRTurn + probPTurn + probSTurn){
            return Action.SCISSORS;
        }
        else if(turn < probRTurn + probPTurn + probSTurn + probLTurn){
            return Action.LIZARD;
        }
        else{
            return Action.SPOCK;
        }
    }

}