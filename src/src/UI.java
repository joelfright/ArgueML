import javax.swing.*;
import java.awt.*;

public class UI {

    JLabel chatbox;

    public void mainWindow(){
        JFrame mainWindow = new JFrame("ArgueML");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(getWindowWidth(),getWindowHeight()));
        mainWindow.add(mainPanel);
        mainPanel.add(chatbox());
        mainPanel.add(simulateButton());
        mainWindow.pack();
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

    public JPanel chatbox(){
        chatbox = new JLabel();
        JPanel chatboxPanel = new JPanel();
        chatboxPanel.add(chatbox);
        int margin = 10;
        chatbox.setLocation(margin,margin);
        chatboxPanel.setPreferredSize(new Dimension(getWindowWidth()-(margin*2),getWindowHeight()-((margin*2)+40)));
        chatboxPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        return chatboxPanel;
    }

    public void changeText(String text){
        chatbox.setText(text);
    }

    public JPanel simulateButton(){
        JButton simulate = new JButton("Simulate");
        JPanel simulatePanel = new JPanel();
        simulatePanel.add(simulate);
        return simulatePanel;
    }

    public int getWindowWidth(){
        return 400;
    }

    public int getWindowHeight(){
        return 500;
    }

}
