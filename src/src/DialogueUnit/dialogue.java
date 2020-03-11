package DialogueUnit;

import MainUnit.agent;
import MainUnit.base;

public class dialogue {

    int i = 0;

    public void argue(agent ag, String argument){
        base.instance.cm.writeCommit(ag, argument);
        base.instance.UI.setChatbox(base.instance.ag.getCurrentTurn().toString() + ": " + argument);
    }

    public void runArgument(){
        while(i < 3) {
            base.instance.pl.initialAssertion();
            base.instance.ag.nextTurn();
            i++;
        }
    }

}
