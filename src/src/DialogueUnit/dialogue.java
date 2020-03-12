package DialogueUnit;

import MainUnit.agent;
import MainUnit.base;

public class dialogue {

    private void argue(agent ag, String argument){
        base.instance.cm.writeCommit(ag, argument, ag.getTurnCounter());
        base.instance.UI.setChatbox(base.instance.ag.getCurrentTurn().toString() + ": " + argument);
    }

    public void runArgument(){
        while(base.instance.ag.getTurnCounter() == 1) {
            argue(base.instance.ag.getCurrentTurn(), base.instance.kb.propositions().get(0)[0]);
            base.instance.ag.nextTurn();
        }
    }

}
