package InterfaceUnit;

import MainUnit.base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class userInterface {

    private JTextArea chatbox = new JTextArea();
    private JTextArea qTable = new JTextArea();
    private JTextField numSim = new JTextField();
    private JTree treeFull;

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
        mainPanel.add(numSimPanel());
        mainPanel.add(runMLPanel());
        mainPanel.add(simulatePanel());
        mainPanel.add(resetPanel());
        mainPanel.add(expandPanel());
        mainPanel.add(qTablePanel());
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

    private JPanel resetPanel(){
        JButton reset = new JButton("Reset");
        reset.addActionListener(ev -> {
            base.instance.dia.resetArgument();
            chatbox.setText("");
        });
        JPanel resetPanel = new JPanel();
        resetPanel.add(reset);

        return resetPanel;
    }

    private JPanel runMLPanel(){
        JButton MLButton = new JButton("Run ML");
        MLButton.addActionListener(ev ->{
            for(int i = 0; i < Integer.parseInt(numSim.getText()); i++){
                base.instance.dia.runArgument();
                base.instance.dia.resetArgument();
                chatbox.setText("");
            }
        });
        JPanel runMLPanel = new JPanel();
        runMLPanel.add(MLButton);

        return runMLPanel;
    }

    private JPanel numSimPanel(){
        numSim.setPreferredSize(new Dimension(50,25));
        JPanel numSimPanel = new JPanel();
        numSimPanel.add(numSim);
        return numSimPanel;
    }

    private JPanel expandPanel(){
        JToggleButton expand = new JToggleButton("Expand");
        expand.addItemListener(ev -> {
            if(ev.getStateChange()==ItemEvent.SELECTED){
                expandAllNodes(treeFull);
            } else if(ev.getStateChange()==ItemEvent.DESELECTED){
                collapseAllNodes(treeFull);
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
        treeScrollPane.setPreferredSize(new Dimension(450, 436));
        treeScrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
        treeScrollPane.setBackground(Color.white);

        return treeScrollPane;
    }

    private JPanel qTablePanel(){
        chatbox.setEditable(false);

        JPanel qTablePane = new JPanel();
        qTablePane.add(qTable);
        qTablePane.setPreferredSize(new Dimension(475, 152));
        qTablePane.setBackground(Color.white);
        qTablePane.setBorder(BorderFactory.createLineBorder(Color.black));

        return qTablePane;
    }

    private void createTree(){
        treeFull = new JTree(base.instance.pl.fullTree);
    }

    private JPanel sidePanelLeft(){
        JPanel sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(450,465));
        sidePanel.add(new JLabel("Full Tree"));
        sidePanel.add(treePanel(treeFull));

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
        return 645;
    }

    public void setQTable(String text){
        qTable.setText(text);
    }

    public void setChatbox(String text){
        chatbox.append("\n" + text);
    }

}
