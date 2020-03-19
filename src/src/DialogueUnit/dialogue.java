package DialogueUnit;

import MainUnit.agent;
import MainUnit.base;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.Random;

public class dialogue {

    private TreeNode currentTree = null;
    private int player, index;
    private boolean[][] used = new boolean[3][2];

    private void argue(agent ag, String argument){
        base.instance.cm.writeCommit(ag, argument, ag.getTurnCounter());
        base.instance.UI.setChatbox("     " + base.instance.ag.getCurrentTurn().toString() + ": " + argument + "     ");
    }

    public void runArgument(){
        base.instance.pl.initPlanning();
        base.instance.pl.buildTree();

        Random rand = new Random();
        do {
            while (base.instance.ag.getTurnCounter() == 1) {
                getCurrentTree();
                argue(base.instance.ag.getCurrentTurn(), currentTree.toString());
                base.instance.ag.nextTurn();
            }
            getCurrentTree();
            do {
                index = rand.nextInt(used.length);
            }while(used[index][player]);
            TreeNode branch = currentTree.getChildAt(index);
            argument(branch);
        }while(!(used[0][0] && used[1][0] && used[2][0] && used[0][1] && used[1][1] && used[2][1]));

    }

    private void getCurrentTree(){
        if(base.instance.ag.getCurrentTurn() == agent.A){
            player = 0;
            currentTree = base.instance.pl.fullTree.getChildAt(0);
        }else{
            player = 1;
            currentTree = base.instance.pl.fullTree.getChildAt(1);
        }
    }

    private void argument(TreeNode branch){
        proposal(branch);
        if(branch.getChildCount() > 1 && Math.random() > 0.5){
            rebuttal(branch.getChildAt(1));
        }
        question(branch);
        evidence(branch);
    }

    private void proposal(TreeNode branch){
        argue(base.instance.ag.getCurrentTurn(), "I think " + branch);
        used[index][player] = true;
        base.instance.ag.nextTurn();
    }

    private void question(TreeNode branch){
        argue(base.instance.ag.getCurrentTurn(), "Why do you think " + branch + "?");
        base.instance.ag.nextTurn();
    }

    private void evidence(TreeNode branch){
        argue(base.instance.ag.getCurrentTurn(), "Because " + branch.getChildAt(0));
        base.instance.ag.nextTurn();
    }

    private void rebuttal(TreeNode branch){
        argue(base.instance.ag.getCurrentTurn(), "I think " + branch);
        base.instance.ag.nextTurn();
        argue(base.instance.ag.getCurrentTurn(), "Why do you think " + branch + "?");
        base.instance.ag.nextTurn();
        argue(base.instance.ag.getCurrentTurn(), "Because " + branch.getChildAt(0));
    }


}
