package MainUnit;

public enum agent {

    A, B;
    agent currentTurn = null;

    public agent currentTurn(){
        return currentTurn;
    }

    public void nextTurn(){
        if(currentTurn() == agent.A){
            currentTurn = agent.B;
        }else{
            currentTurn = agent.A;
        }
    }

    public void reset(){
        currentTurn = agent.A;
    }

}
