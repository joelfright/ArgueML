package DialogueUnit;

import MainUnit.agent;
import MainUnit.base;

import javax.swing.tree.TreeNode;
import java.util.Random;

public class dialogue {

    private TreeNode currentTree = null;
    private TreeNode currentBranch;
    private int player, index;
    private boolean[][] used = new boolean[3][2];
    private double randomness = 0.5;
    private boolean alreadyExecuted = false;

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
        }while(!(used[0][0] && used[1][0] && used[2][0] && used[0][1] && used[1][1] && used[2][1]));

        System.out.println("A: ");
        base.instance.ql.printQTable(0);
        System.out.println("B: ");
        base.instance.ql.printQTable(1);
    }

    public TreeNode getCurrentBranch(){
        return currentBranch;
    }

    private void setCurrentBranch(TreeNode branch){
        this.currentBranch = branch;
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
        setCurrentBranch(branch);
        makeMove(base.instance.ag.getCurrentTurn(), "I think " + branch);
        updateQTable(0,getReward(1));
        used[index][player] = true;
        base.instance.ag.nextTurn();
    }

    private void question(TreeNode branch){
        makeMove(base.instance.ag.getCurrentTurn(), "Why do you think " + branch + "?");
        setCurrentBranch(branch);
        base.instance.ag.nextTurn();
    }

    private void evidence(TreeNode branch){
        makeMove(base.instance.ag.getCurrentTurn(), "Because " + branch.getChildAt(0));
        setCurrentBranch(branch.getChildAt(0));
        updateQTable(1,getReward(2));
        base.instance.ag.nextTurn();
    }

    private void rebuttal(TreeNode branch){
        makeMove(base.instance.ag.getCurrentTurn(), "I think " + branch);
        setCurrentBranch(branch);
        updateQTable(0,getReward(1));
        base.instance.ag.nextTurn();
        if(Math.random() > randomness){
            makeMove(base.instance.ag.getCurrentTurn(), "Why do you think " + branch + "?");
            base.instance.ag.nextTurn();
            if(branch.getChildCount() == 1) {
                makeMove(base.instance.ag.getCurrentTurn(), "Because " + branch.getChildAt(0));
                setCurrentBranch(branch.getChildAt(0));
            }else{
                Random rand = new Random();
                int i = rand.nextInt(2);
                makeMove(base.instance.ag.getCurrentTurn(), "Because " + branch.getChildAt(i));
                setCurrentBranch(branch.getChildAt(i));
            }

            updateQTable(1, getReward(2));
        }else{
            base.instance.ag.nextTurn();
        }
    }

    public int getReward(int level){
        if(level == 1){
            return Integer.parseInt(currentBranch.getChildAt(0).getChildAt(0).toString());
        }else{
            return Integer.parseInt(currentBranch.getChildAt(0).toString());
        }
    }

    public void updateQTable(int actionType, int reward){
        int level = 0;
        int position = 0;
        if(getPlayer() == 0){
            for(int i = 0; i < base.instance.pl.forCP.size(); i++){
                if(getCurrentBranch().toString().equals(base.instance.pl.forCP.get(i)[0])){
                    level = Integer.parseInt(base.instance.pl.forCP.get(i)[2]);
                    position = Integer.parseInt(base.instance.pl.forCP.get(i)[3]);
                    break;
                }
            }
            base.instance.ql.QTableFor[level][position][0] = base.instance.ql.calcReward(level,position,reward,0);
        }else{
            for(int i = 0; i < base.instance.pl.againstCP.size(); i++){
                if(getCurrentBranch().toString().equals(base.instance.pl.againstCP.get(i)[0])){
                    level = Integer.parseInt(base.instance.pl.againstCP.get(i)[2]);
                    position = Integer.parseInt(base.instance.pl.againstCP.get(i)[3]);
                    if(Integer.parseInt(base.instance.pl.againstCP.get(i)[4]) == 13){
                        level = 1; position = 5;
                        break;
                    }
                    if(Integer.parseInt(base.instance.pl.againstCP.get(i)[4]) == 15){
                        level = 0; position = 5;
                        break;
                    }
                    break;
                }
            }
            base.instance.ql.QTableAg[level][position][actionType] = base.instance.ql.calcReward(level,position,reward,1);
        }
    }

    private int getPlayer(){
        return base.instance.ag.getCurrentTurn() == agent.A ? 0 : 1;
    }

    public void resetArgument(){
        for(int i = 0; i < used.length; i++){
            used[i][0] = false;
            used[i][1] = false;
        }
        base.instance.cm.clearCommit();
        base.instance.ag.reset();
    }

}
