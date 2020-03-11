package PlanningUnit;

import MainUnit.base;

public class planning {


    public void initialAssertion(){
        if(base.instance.turn < 2){
            if(base.instance.ag.proposition())
                base.instance.dia.argue(base.instance.ag.getCurrentTurn(),"I believe CP is acceptable");
            else{
                base.instance.dia.argue(base.instance.ag.getCurrentTurn(),"I believe CP is not acceptable");
            }
        }
    }

}
