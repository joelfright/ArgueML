package PlanningUnit;

import MainUnit.agent;
import MainUnit.base;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class planning {

    public DefaultMutableTreeNode forCPTree = new DefaultMutableTreeNode("CP is acceptable");
    public DefaultMutableTreeNode againstCPTree = new DefaultMutableTreeNode("CP is not acceptable");
    private ArrayList<String[]> forCP;
    private ArrayList<String[]> againstCP;

    public void initPlanning(){
        forCP = base.instance.kb.propositions(agent.A);
        againstCP  = base.instance.kb.propositions(agent.B);
    }

    private DefaultMutableTreeNode addLeaf(String argument, DefaultMutableTreeNode parent, Enum ag){
        DefaultMutableTreeNode leaf;
        leaf = new DefaultMutableTreeNode(argument);
        parent.add(leaf);
        return leaf;
    }

    public void buildAgent1() {
        DefaultMutableTreeNode deterrent, CPBack, murders, notDeterrent, notMurders;

        deterrent = addLeaf(forCP.get(1)[0], forCPTree,agent.A);
        addLeaf(forCP.get(3)[0], deterrent,agent.A);
        notDeterrent = addLeaf(againstCP.get(1)[0],deterrent,agent.B);
        addLeaf(againstCP.get(11)[0],notDeterrent,agent.B);

        CPBack = addLeaf(forCP.get(8)[0],forCPTree,agent.A);
        addLeaf(forCP.get(14)[0],CPBack,agent.A);

        murders = addLeaf(forCP.get(9)[0], forCPTree,agent.A);
        addLeaf(forCP.get(5)[0],murders,agent.A);
        notMurders = addLeaf(againstCP.get(6)[0],murders,agent.B);
        addLeaf(againstCP.get(14)[0],notMurders,agent.B);
        addLeaf(againstCP.get(13)[0],notMurders,agent.B);
    }

    public void buildAgent2(){
        DefaultMutableTreeNode humanLife, innoPeople, rehab, notHumanLife, unlikeInnoPeep;

        humanLife = addLeaf(againstCP.get(5)[0],againstCPTree,agent.B);
        addLeaf(againstCP.get(3)[0],humanLife,agent.B);
        notHumanLife = addLeaf(againstCP.get(7)[0],humanLife,agent.A);
        addLeaf(againstCP.get(2)[0],notHumanLife,agent.A);

        innoPeople = addLeaf(againstCP.get(4)[0],againstCPTree,agent.B);
        addLeaf(againstCP.get(9)[0],innoPeople,agent.B);
        unlikeInnoPeep = addLeaf(againstCP.get(6)[0],innoPeople,agent.A);
        addLeaf(againstCP.get(11)[0],unlikeInnoPeep,agent.A);

        rehab = addLeaf(againstCP.get(14)[0],againstCPTree,agent.B);
        addLeaf(againstCP.get(13)[0],rehab,agent.B);

    }

}
