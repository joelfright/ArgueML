package MainUnit;

public enum agent {

    A, B;
    agent currentTurn = null;
    int turn = 0;

    public agent getCurrentTurn(){
        return currentTurn;
    }

    public void nextTurn(){
        if(currentTurn == agent.A){
            currentTurn = agent.B;
            agent.B.turn++;
        }else if(currentTurn == agent.B){
            currentTurn = agent.A;
            agent.A.turn++;
        }
    }

    public int getTurnCounter(){
        if(currentTurn == agent.A){
            return agent.A.turn;
        }else{
            return agent.B.turn;
        }
    }

    public void reset(){
        currentTurn = agent.A;
        agent.A.turn = 1;
    }

}
