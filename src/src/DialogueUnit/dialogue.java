package DialogueUnit;

import MainUnit.agent;
import MainUnit.base;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.Random;

public class dialogue {

    private TreeNode currentTree = null;

    private void argue(agent ag, String argument){
        base.instance.cm.writeCommit(ag, argument, ag.getTurnCounter());
        base.instance.UI.setChatbox("     " + base.instance.ag.getCurrentTurn().toString() + ": " + argument);
    }

    public void runArgument(){
        base.instance.pl.initPlanning();
        base.instance.pl.buildTree();

        Random rand = new Random();
        for(int i = 0; i < 2; i++){
            while(base.instance.ag.getTurnCounter() == 1) {
                getCurrentTree();
                argue(base.instance.ag.getCurrentTurn(), currentTree.toString());
                base.instance.pl.addLeaf(currentTree.toString(),base.instance.pl.usedTree);
                base.instance.ag.nextTurn();
            }
            getCurrentTree();
            TreeNode branch = currentTree.getChildAt(rand.nextInt(3));
            argue(branch);
        }
    }

    private TreeNode getCurrentTree(){
        if(base.instance.ag.getCurrentTurn() == agent.A){
            currentTree = base.instance.pl.fullTree.getChildAt(0);
        }else{
            currentTree = base.instance.pl.fullTree.getChildAt(1);
        }
        return currentTree;
    }


    private void proposal(TreeNode branch){
        argue(base.instance.ag.getCurrentTurn(), "I think " + branch.toString());
        if(base.instance.ag.getCurrentTurn() == agent.A){
            base.instance.pl.addLeaf(branch.toString(), (DefaultMutableTreeNode) base.instance.pl.usedTree.getChildAt(0));
        }else{
            base.instance.pl.addLeaf(branch.toString(), (DefaultMutableTreeNode) base.instance.pl.usedTree.getChildAt(1));
        }
        base.instance.ag.nextTurn();
    }

    private void question(TreeNode branch){
        argue(base.instance.ag.getCurrentTurn(), "Why do you think " + branch.toString() + "?");
        base.instance.ag.nextTurn();
    }

    private void evidence(TreeNode branch){
        argue(base.instance.ag.getCurrentTurn(), "Because " + branch.getChildAt(0).toString());
        if(base.instance.ag.getCurrentTurn() == agent.A){
            base.instance.pl.addLeaf(branch.getChildAt(0).toString(), (DefaultMutableTreeNode) base.instance.pl.usedTree.getChildAt(0).getChildAt(0));
        }else{
            base.instance.pl.addLeaf(branch.getChildAt(0).toString(), (DefaultMutableTreeNode) base.instance.pl.usedTree.getChildAt(1).getChildAt(0));
        }
        base.instance.ag.nextTurn();
    }

    private void rebuttal(TreeNode branch){
        proposal(branch.getChildAt(1));
        question(branch.getChildAt(1));
        evidence(branch.getChildAt(1));
    }

    private void argue(TreeNode branch){
        proposal(branch);
        if(branch.getChildCount() > 1){
            rebuttal(branch);
        }else {
            question(branch);
            evidence(branch);
        }
    }

}
