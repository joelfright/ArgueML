package InterfaceUnit;

import MainUnit.base;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.stream.IntStream;

public class userInterface {

    private JTextArea chatbox = new JTextArea();
    private JTree treeFor;
    private JTree treeAG;

    public void mainWindow(){
        JFrame mainWindow = new JFrame("ArgueML");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.addWindowListener(new windowListener());

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(getWindowWidth(),getWindowHeight()));

        createTree();

        mainWindow.add(mainPanel);
        mainPanel.add(sidePanelRight());
        mainPanel.add(sidePanelLeft());
        mainPanel.add(simulatePanel());
        mainPanel.add(expandPanel());
        mainWindow.pack();

        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

    private JScrollPane chatboxPanel() {
        chatbox.setEditable(false);

        JScrollPane chatboxPane = new JScrollPane(chatbox);
        chatboxPane.setPreferredSize(new Dimension(450, 436));
        chatboxPane.setBackground(Color.white);
        chatboxPane.setBorder(BorderFactory.createLineBorder(Color.black));

        return chatboxPane;
    }

    private JPanel simulatePanel(){
        JButton simulate = new JButton("Simulate");
        simulate.addActionListener(e -> base.instance.dia.runArgument());
        JPanel simulatePanel = new JPanel();
        simulatePanel.add(simulate);

        return simulatePanel;
    }

    private JPanel expandPanel(){
        JToggleButton expand = new JToggleButton("Expand");
        expand.addItemListener(ev -> {
            if(ev.getStateChange()==ItemEvent.SELECTED){
                expandAllNodes(treeFor);
                expandAllNodes(treeAG);
            } else if(ev.getStateChange()==ItemEvent.DESELECTED){
                collapseAllNodes(treeFor);
                collapseAllNodes(treeAG);
            }
        });
        JPanel expandPanel = new JPanel();
        expandPanel.add(expand);

        return expandPanel;
    }

    private JScrollPane treePanel(JTree tree){
        JScrollPane treeScrollPane = new JScrollPane(tree);

        treeScrollPane.setLocation(10,10);
        treeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        treeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        treeScrollPane.setPreferredSize(new Dimension(450, 205));
        treeScrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
        treeScrollPane.setBackground(Color.white);

        return treeScrollPane;
    }

    private void createTree(){
        treeFor = new JTree(base.instance.pl.forCPTree);
        treeAG = new JTree(base.instance.pl.againstCPTree);
    }


    private JPanel sidePanelLeft(){
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(450,465));
        sidePanel.add(new JLabel("Agent A"));
        sidePanel.add(treePanel(treeFor));
        sidePanel.add(new JLabel("Agent B"));
        sidePanel.add(treePanel(treeAG));

        return sidePanel;
    }

    private JPanel sidePanelRight(){
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(450,465));
        sidePanel.add(new JLabel("Chatbox"));
        sidePanel.add(chatboxPanel());

        return sidePanel;
    }

    private void expandAllNodes(JTree tree) {
        int i = 0;
        while (i < tree.getRowCount()) {
            tree.expandRow(i);
            i++;
        }
    }

    private void collapseAllNodes(JTree tree) {
        int i = 0;
        while (i < tree.getRowCount()) {
            tree.collapseRow(i);
            i++;
        }
    }

    private int getWindowWidth(){
        return 940;
    }

    private int getWindowHeight(){
        return 520;
    }

    public void setChatbox(String text){
        chatbox.append("\n" + text);
    }

}
