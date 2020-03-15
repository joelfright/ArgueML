package InterfaceUnit;

import MainUnit.base;

import javax.swing.*;
import java.awt.*;

public class userInterface {

    private JTextArea chatbox = new JTextArea();


    public void mainWindow(){
        JFrame mainWindow = new JFrame("ArgueML");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.addWindowListener(new windowListener());

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(getWindowWidth(),getWindowHeight()));

        mainWindow.add(mainPanel);
        mainPanel.add(chatboxPanel());
        mainPanel.add(simulatePanel());
        mainWindow.pack();

        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

    private JPanel chatboxPanel() {
        JPanel chatboxPanel = new JPanel();
        chatboxPanel.setBackground(Color.white);
        chatbox.setEditable(false);
        chatboxPanel.add(chatbox);
        int margin = 10;
        chatbox.setLocation(margin, margin);
        chatboxPanel.setPreferredSize(new Dimension(getWindowWidth() - (margin * 2), getWindowHeight() - ((margin * 2) + 30)));
        chatboxPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        return chatboxPanel;
    }

    private JPanel simulatePanel(){
        JButton simulate = new JButton("Simulate");
        simulate.addActionListener(e -> base.instance.dia.runArgument());
        JPanel simulatePanel = new JPanel();
        simulatePanel.add(simulate);

        return simulatePanel;
    }

    private int getWindowWidth(){
        return 400;
    }

    private int getWindowHeight(){
        return 500;
    }

    public void setChatbox(String text){
        chatbox.append("\n" + text);
    }

    public JTextArea getChatbox(){
        return this.chatbox;
    }

}
