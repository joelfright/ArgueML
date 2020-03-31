package DialogueUnit;

import MainUnit.agent;
import MainUnit.base;

import javax.swing.tree.TreeNode;
import java.util.Random;

public class dialogue {

    private TreeNode currentTree = null;
    private int player, index;
    private boolean[][] used = new boolean[3][2];
    private double randomness = 0.5;
    private boolean alreadyExecuted = false;
    private int[] rewardTotal = new int[2];

    private void makeMove(agent ag, String argument){
        base.instance.cm.writeCommit(ag, argument, ag.getTurnCounter());
        base.instance.UI.setChatbox("     " + base.instance.ag.getCurrentTurn().toString() + ": " + argument + "     ");
    }

    public void runArgument(){
        if(!alreadyExecuted) {
            base.instance.pl.initPlanning();
            base.instance.pl.buildTree();
            alreadyExecuted = true;
        }

        Random rand = new Random();
        do {
            while (base.instance.ag.getTurnCounter() == 1) {
                getCurrentTree();
                makeMove(base.instance.ag.getCurrentTurn(), currentTree.toString());
                base.instance.ag.nextTurn();
            }
            getCurrentTree();
            do {
                index = rand.nextInt(used.length);
            }while(used[index][player]);
            TreeNode branch = currentTree.getChildAt(index);
            argument(branch);
            if(index == 2 && player == 1){
                appendReward(Integer.parseInt(branch.getChildAt(1).toString()));
            }
            base.instance.ql.addReward(0,index,index,10,getPlayer());
        }while(!(used[0][0] && used[1][0] && used[2][0] && used[0][1] && used[1][1] && used[2][1]));

        System.out.println("A: ");
        base.instance.ql.printQTable(0);
        System.out.println("B: ");
        base.instance.ql.printQTable(1);
    }

    private void getCurrentTree(){
        if(base.instance.ag.getCurrentTurn() == agent.A){
            player = 0;
        }else{
            player = 1;
        }
        currentTree = base.instance.pl.fullTree.getChildAt(player);
    }

    private void argument(TreeNode branch){
        proposal(branch);
        if(branch.getChildCount() > 1  && Math.random() > randomness && branch.getChildAt(1).getAllowsChildren()){
            rebuttal(branch.getChildAt(1));
        }
        if(Math.random() > randomness){
            question(branch);
            evidence(branch);
        }
    }

    private void proposal(TreeNode branch){
        makeMove(base.instance.ag.getCurrentTurn(), "I think " + branch);
        used[index][player] = true;
        base.instance.ag.nextTurn();
    }

    private void question(TreeNode branch){
        makeMove(base.instance.ag.getCurrentTurn(), "Why do you think " + branch + "?");
        base.instance.ag.nextTurn();
    }

    private void evidence(TreeNode branch){
        makeMove(base.instance.ag.getCurrentTurn(), "Because " + branch.getChildAt(0));
        appendReward(Integer.parseInt(branch.getChildAt(0).getChildAt(0).toString()));
        base.instance.ag.nextTurn();
    }

    private void rebuttal(TreeNode branch){
        makeMove(base.instance.ag.getCurrentTurn(), "I think " + branch);
        base.instance.ag.nextTurn();

        if(Math.random() > randomness){
            makeMove(base.instance.ag.getCurrentTurn(), "Why do you think " + branch + "?");
            base.instance.ag.nextTurn();
            if(branch.getChildCount() == 1) {
                makeMove(base.instance.ag.getCurrentTurn(), "Because " + branch.getChildAt(0));
                appendReward(Integer.parseInt(branch.getChildAt(0).getChildAt(0).toString()));
            }else{
                Random rand = new Random();
                int i = rand.nextInt(2);
                makeMove(base.instance.ag.getCurrentTurn(), "Because " + branch.getChildAt(i));
                appendReward(Integer.parseInt(branch.getChildAt(i).getChildAt(0).toString()));
            }
        }else{
            base.instance.ag.nextTurn();
        }
    }

    private int getPlayer(){
        return base.instance.ag.getCurrentTurn() == agent.A ? 0 : 1;
    }

    private void appendReward(int reward){
        rewardTotal[getPlayer()] = rewardTotal[getPlayer()] + reward;
    }

    private int getReward(int player){
        return rewardTotal[player];
    }

    public void resetArgument(){
        for(int i = 0; i < used.length; i++){
            used[i][0] = false;
            used[i][1] = false;
        }
        rewardTotal[0] = 0;
        rewardTotal[1] = 0;
        base.instance.cm.clearCommit();
        base.instance.ag.reset();
    }

}
