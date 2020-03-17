package PlanningUnit;

import MainUnit.agent;
import MainUnit.base;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class planning {

    public DefaultMutableTreeNode fullTree = new DefaultMutableTreeNode("Full");
    public DefaultMutableTreeNode usedTree = new DefaultMutableTreeNode("Used");
    private ArrayList<String[]> forCP;
    private ArrayList<String[]> againstCP;

    public void initPlanning(){
        forCP = base.instance.kb.propositions(agent.A);
        againstCP  = base.instance.kb.propositions(agent.B);
    }

    public DefaultMutableTreeNode addLeaf(String argument, DefaultMutableTreeNode parent){
        DefaultMutableTreeNode leaf;
        leaf = new DefaultMutableTreeNode(argument);
        parent.add(leaf);
        return leaf;
    }

    public void buildTree() {
        // for CP
        DefaultMutableTreeNode forCPTree, deterrent, CPBack, murders, notDeterrent, notMurders;

        forCPTree = addLeaf(forCP.get(0)[0],fullTree);

        deterrent = addLeaf(forCP.get(1)[0], forCPTree);
        addLeaf(forCP.get(3)[0], deterrent);
        notDeterrent = addLeaf(againstCP.get(1)[0],deterrent);
        addLeaf(againstCP.get(11)[0],notDeterrent);

        CPBack = addLeaf(forCP.get(8)[0],forCPTree);
        addLeaf(forCP.get(14)[0],CPBack);

        murders = addLeaf(forCP.get(9)[0], forCPTree);
        addLeaf(forCP.get(5)[0],murders);
        notMurders = addLeaf(againstCP.get(6)[0],murders);
        addLeaf(againstCP.get(14)[0],notMurders);
        addLeaf(againstCP.get(13)[0],notMurders);

        // against CP
        DefaultMutableTreeNode againstCPTree, humanLife, innoPeople, rehab, notHumanLife, unlikeInnoPeep;

        againstCPTree = addLeaf(againstCP.get(0)[0],fullTree);

        humanLife = addLeaf(againstCP.get(5)[0],againstCPTree);
        addLeaf(againstCP.get(3)[0],humanLife);
        notHumanLife = addLeaf(forCP.get(7)[0],humanLife);
        addLeaf(forCP.get(2)[0],notHumanLife);

        innoPeople = addLeaf(againstCP.get(4)[0],againstCPTree);
        addLeaf(againstCP.get(9)[0],innoPeople);
        unlikeInnoPeep = addLeaf(forCP.get(6)[0],innoPeople);
        addLeaf(forCP.get(11)[0],unlikeInnoPeep);

        rehab = addLeaf(againstCP.get(14)[0],againstCPTree);
        addLeaf(againstCP.get(13)[0],rehab);
    }

}
