package DialogueUnit;

import MainUnit.agent;
import MainUnit.base;

import javax.swing.tree.DefaultMutableTreeNode;

public class dialogue {

    private void argue(agent ag, String argument){
        base.instance.cm.writeCommit(ag, argument, ag.getTurnCounter());
        base.instance.UI.setChatbox(base.instance.ag.getCurrentTurn().toString() + ": " + argument);
    }

    public void runArgument(){
        base.instance.pl.initPlanning();
        base.instance.pl.buildAgent1();
        base.instance.pl.buildAgent2();
        DefaultMutableTreeNode test;
        while(base.instance.ag.getTurnCounter() == 1) {
            if(base.instance.ag.getCurrentTurn() == agent.A){
                 test = base.instance.pl.forCPTree;
            }else{
                test = base.instance.pl.againstCPTree;
            }
            argue(base.instance.ag.getCurrentTurn(), test.getFirstChild().toString());
            base.instance.ag.nextTurn();
        }
    }

}
