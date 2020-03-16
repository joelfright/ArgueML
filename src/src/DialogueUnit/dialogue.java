package DialogueUnit;

import MainUnit.agent;
import MainUnit.base;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Random;

public class dialogue {

    private DefaultMutableTreeNode currentTree = null;

    private void argue(agent ag, String argument){
        base.instance.cm.writeCommit(ag, argument, ag.getTurnCounter());
        base.instance.UI.setChatbox("     " + base.instance.ag.getCurrentTurn().toString() + ": " + argument);
    }

    public void runArgument(){
        base.instance.pl.initPlanning();
        base.instance.pl.buildAgent1();
        base.instance.pl.buildAgent2();

        Random rand = new Random();
        for(int i = 0; i < 2; i++){
            while(base.instance.ag.getTurnCounter() == 1) {
                getCurrentTree();
                argue(base.instance.ag.getCurrentTurn(), currentTree.toString());
                base.instance.ag.nextTurn();
            }
            getCurrentTree();
            TreeNode branch = currentTree.getChildAt(rand.nextInt(3));
            proposal(branch);
            question(branch);
            evidence(branch);
        }
    }

    private DefaultMutableTreeNode getCurrentTree(){
        if(base.instance.ag.getCurrentTurn() == agent.A){
            currentTree = base.instance.pl.forCPTree;
        }else{
            currentTree = base.instance.pl.againstCPTree;
        }
        return currentTree;
    }

    private void proposal(TreeNode branch){
        argue(base.instance.ag.getCurrentTurn(), "I think " + branch.toString());
        base.instance.ag.nextTurn();
    }

    private void question(TreeNode branch){
        argue(base.instance.ag.getCurrentTurn(), "Why do you think " + branch.toString() + "?");
        base.instance.ag.nextTurn();
    }

    private void evidence(TreeNode branch){
        argue(base.instance.ag.getCurrentTurn(), "Because " + branch.getChildAt(0).toString());
        base.instance.ag.nextTurn();
    }

}
