package MainUnit;

public enum agent {

    A, B;
    agent currentTurn = null;

    public agent getCurrentTurn(){
        return currentTurn;
    }

    public void nextTurn(){
        base.instance.turn++;
        if(currentTurn == agent.A){
            currentTurn = agent.B;
        }else if(currentTurn == agent.B){
            currentTurn = agent.A;
        }
    }

    public void reset(){
        currentTurn = agent.A;
    }

    public boolean proposition(){
        if(currentTurn == agent.A){
            return true;
        }else{
            return false;
        }
    }

}
