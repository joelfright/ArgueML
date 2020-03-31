package MLUnit;

import java.text.DecimalFormat;

public class qLearning {

    private double[][] QTableFor = new double[4][3];
    private double[][] QTableAg = new double[4][3];
    private static DecimalFormat df = new DecimalFormat("0.00");

    public void addReward(int state, int action, int newState, int reward, int player){
        double gamma = 0.8;
        int alpha = 1;
        if(player == 0) {
            QTableFor[state][action] = (1- alpha)*state + alpha *(reward + (gamma * findMax(QTableFor, newState)));
        }
        if(player == 1){
            QTableAg[state][action] = (1- alpha)*state + alpha *(reward + (gamma * findMax(QTableAg, newState)));
        }
    }

    private double findMax(double[][] QTable, int state){
        double maximum = QTable[state][0];
        for (int i = 1 ; i < QTable[state].length; i++) {
            if (QTable[state][i] > maximum) {
                maximum = QTable[state][i];
            }
        }
        return maximum;
    }

    public void printQTable(int player){
        double[][] QTable;
        if(player == 1) QTable = QTableFor;
        else QTable = QTableAg;
        for(int i = 0; i < 4 ;i++){
            System.out.print("[");
            for(int j = 0; j < 3;j++){
                System.out.print(" " + df.format(QTable[i][j]) + " ");
            }
            System.out.println("]");
        }
    }

}
