package PlanningUnit;

import MainUnit.agent;
import MainUnit.base;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class planning {

    public DefaultMutableTreeNode fullTree = new DefaultMutableTreeNode("Full");
    private ArrayList<String[]> forCP, againstCP;
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
        DefaultMutableTreeNode notMurdersEvid, notDeterrentEvid, deterrentEvid, CPBackEvid, murdersEvid;

        forCPTree = addLeaf(forCP.get(0)[0],fullTree);

        deterrent = addLeaf(forCP.get(1)[0], forCPTree);
        deterrentEvid = addLeaf(forCP.get(3)[0], deterrent);
        notDeterrent = addLeaf(againstCP.get(1)[0],deterrent);
        notDeterrentEvid = addLeaf(againstCP.get(11)[0],notDeterrent);

        CPBack = addLeaf(forCP.get(8)[0],forCPTree);
        CPBackEvid = addLeaf(forCP.get(14)[0],CPBack);

        murders = addLeaf(forCP.get(9)[0], forCPTree);
        murdersEvid = addLeaf(forCP.get(5)[0],murders);
        notMurders = addLeaf(againstCP.get(6)[0],murders);
        addLeaf(againstCP.get(14)[0],notMurders);
        notMurdersEvid = addLeaf(againstCP.get(13)[0],notMurders);

        //reward for
        addLeafInt(reward[0],notDeterrentEvid).setAllowsChildren(false);
        addLeafInt(reward[1],murdersEvid).setAllowsChildren(false);
        addLeafInt(reward[2],notMurdersEvid).setAllowsChildren(false);
        addLeafInt(reward[4],CPBackEvid).setAllowsChildren(false);
        addLeafInt(reward[5],deterrentEvid).setAllowsChildren(false);

        // against CP
        DefaultMutableTreeNode againstCPTree, humanLife, innoPeople, rehab, notHumanLife, unlikeInnoPeep;
        DefaultMutableTreeNode innoPeopleEvid, unlikeInnoPeopleEvid, rehabEvid;

        againstCPTree = addLeaf(againstCP.get(0)[0],fullTree);

        humanLife = addLeaf(againstCP.get(5)[0],againstCPTree);
        addLeaf(againstCP.get(3)[0],humanLife);
        notHumanLife = addLeaf(forCP.get(7)[0],humanLife);
        addLeaf(forCP.get(2)[0],notHumanLife);

        innoPeople = addLeaf(againstCP.get(4)[0],againstCPTree);
        innoPeopleEvid = addLeaf(againstCP.get(9)[0],innoPeople);
        unlikeInnoPeep = addLeaf(forCP.get(6)[0],innoPeople);
        unlikeInnoPeopleEvid = addLeaf(forCP.get(11)[0],unlikeInnoPeep);

        rehab = addLeaf(againstCP.get(14)[0],againstCPTree);
        rehabEvid = addLeaf(againstCP.get(13)[0],rehab);

        //reward against
        addLeafInt(reward[2],rehabEvid).setAllowsChildren(false);
        addLeafInt(reward[3],innoPeopleEvid).setAllowsChildren(false);
        addLeafInt(reward[6],rehab).setAllowsChildren(false);
        addLeafInt(reward[7],unlikeInnoPeopleEvid).setAllowsChildren(false);

    }

}
