package MLUnit;

import java.text.DecimalFormat;

public class qLearning {

    public double[][] QTableFor = new double[3][6];
    public double[][] QTableAg = new double[3][6];
    private static DecimalFormat df = new DecimalFormat("0.00");

    public double calcReward(int state, int reward, int player){
        double gamma = 0.8;
        int alpha = 1;
        double newReward = 0;
        if(player == 0) {
            newReward = (1- alpha)*state + alpha *(reward + (gamma * findMax(QTableFor, state)));
        }
        if(player == 1){
            newReward = (1- alpha)*state + alpha *(reward + (gamma * findMax(QTableAg, state)));
        }
        return newReward;
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
        if(player == 0) QTable = QTableFor;
        else QTable = QTableAg;
        for (double[] doubles : QTable) {
                System.out.print("[");
                for (int j = 0; j < QTable[1].length; j++) {
                    System.out.print(" " + df.format(doubles[j]) + " ");
                }
                System.out.println("]");

        }
    }

}
