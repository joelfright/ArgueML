package PlanningUnit;

import MainUnit.agent;
import MainUnit.base;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class planning {

    public DefaultMutableTreeNode fullTree = new DefaultMutableTreeNode("Full");
    public ArrayList<String[]> forCP, againstCP;
    private int[] reward;

    public void initPlanning(){
        forCP = base.instance.kb.propositions(agent.A);
        againstCP  = base.instance.kb.propositions(agent.B);
        reward = base.instance.rw.getReward();
    }

    private DefaultMutableTreeNode addLeaf(String argument, DefaultMutableTreeNode parent){
        DefaultMutableTreeNode leaf = new DefaultMutableTreeNode(argument);
        parent.add(leaf);
        return leaf;
    }

    private DefaultMutableTreeNode addLeafInt(int reward, DefaultMutableTreeNode parent){
        DefaultMutableTreeNode leaf = new DefaultMutableTreeNode(reward);
        parent.add(leaf);
        return leaf;
    }

    public void buildTree() {
        // for CP
        DefaultMutableTreeNode forCPTree, deterrent, CPBack, murders, notDeterrent, notMurders;
        DefaultMutableTreeNode notMurdersEvid1, notMurdersEvid2, notDeterrentEvid, deterrentEvid, CPBackEvid, murdersEvid;

        forCPTree = addLeaf(forCP.get(0)[0],fullTree);

        deterrent = addLeaf(forCP.get(1)[0], forCPTree); //1 FOR
        deterrentEvid = addLeaf(forCP.get(3)[0], deterrent); //2 FOR
        notDeterrent = addLeaf(againstCP.get(1)[0],deterrent);  //7 AGAINST
        notDeterrentEvid = addLeaf(againstCP.get(11)[0],notDeterrent); //8 AGAINST

        CPBack = addLeaf(forCP.get(8)[0],forCPTree); //3 FOR
        CPBackEvid = addLeaf(forCP.get(14)[0],CPBack); //4 FOR

        murders = addLeaf(forCP.get(9)[0], forCPTree); //5 FOR
        murdersEvid = addLeaf(forCP.get(5)[0],murders); //6 FOR
        notMurders = addLeaf(againstCP.get(6)[0],murders); //9 AGAINST
        notMurdersEvid1 = addLeaf(againstCP.get(14)[0],notMurders); //10 AGAINST
        notMurdersEvid2 = addLeaf(againstCP.get(16)[0],notMurders); //11 AGAINST

        //reward for
        addLeafInt(reward[0],notDeterrentEvid).setAllowsChildren(false);
        addLeafInt(reward[1],murdersEvid).setAllowsChildren(false);
        addLeafInt(reward[2],notMurdersEvid1).setAllowsChildren(false);
        addLeafInt(reward[4],CPBackEvid).setAllowsChildren(false);
        addLeafInt(reward[5],deterrentEvid).setAllowsChildren(false);
        addLeafInt(reward[6],notMurdersEvid2).setAllowsChildren(false);

        // against CP
        DefaultMutableTreeNode againstCPTree, humanLife, innoPeople, rehab, notHumanLife, unlikeInnoPeep;
        DefaultMutableTreeNode innoPeopleEvid, unlikeInnoPeopleEvid, rehabEvid, humanLifeEvid, notHumanLifeEvid;

        againstCPTree = addLeaf(againstCP.get(0)[0],fullTree);

        humanLife = addLeaf(againstCP.get(5)[0],againstCPTree); //1 AGAINST
        humanLifeEvid = addLeaf(againstCP.get(3)[0],humanLife); //2 AGAINST
        notHumanLife = addLeaf(forCP.get(7)[0],humanLife); //7 FOR
        notHumanLifeEvid = addLeaf(forCP.get(2)[0],notHumanLife); //8 FOR

        innoPeople = addLeaf(againstCP.get(4)[0],againstCPTree); //3 AGAINST
        innoPeopleEvid = addLeaf(againstCP.get(9)[0],innoPeople); //4 AGAINST
        unlikeInnoPeep = addLeaf(forCP.get(6)[0],innoPeople); //9 FOR
        unlikeInnoPeopleEvid = addLeaf(forCP.get(11)[0],unlikeInnoPeep); //10 FOR

        rehab = addLeaf(againstCP.get(15)[0],againstCPTree); //5 AGAINST
        rehabEvid = addLeaf(againstCP.get(13)[0],rehab); //6 AGAINST

        //reward against
        addLeafInt(reward[2],rehabEvid).setAllowsChildren(false);
        addLeafInt(reward[3],innoPeopleEvid).setAllowsChildren(false);
        addLeafInt(reward[6],rehab).setAllowsChildren(false);
        addLeafInt(reward[7],unlikeInnoPeopleEvid).setAllowsChildren(false);
        addLeafInt(reward[8],humanLifeEvid).setAllowsChildren(false);
        addLeafInt(reward[8],notHumanLifeEvid).setAllowsChildren(false);

    }

}
