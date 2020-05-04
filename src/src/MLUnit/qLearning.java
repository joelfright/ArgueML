package MLUnit;

import java.text.DecimalFormat;

public class qLearning {

    public double[][][] QTableFor = new double[3][6][2];
    public double[][][] QTableAg = new double[3][6][2];
    private static DecimalFormat df = new DecimalFormat("0.00");

    public double calcReward(int state,int action, int reward, int player){
        double gamma = 0.8;
        int alpha = 1;
        double newReward = 0;
        if(player == 0) {
            newReward = (1- alpha)*state + alpha *(reward + (gamma * findMax(QTableFor, state,action)));
        }
        if(player == 1){
            newReward = (1- alpha)*state + alpha *(reward + (gamma * findMax(QTableAg, state,action)));
        }
        return newReward;
    }

    private double findMax(double[][][] QTable, int state, int action){
        if(state != 0){
            state = state - 1;
        }
        double maximum = QTable[state][action][0];
        for (int i = 1 ; i < QTable[0][0].length; i++) {
            if (QTable[state][action][i] > maximum) {
                maximum = QTable[state][action][i];
            }
        }
        return maximum;
    }

    public String printQTable(int player){
        StringBuilder QTableOutput = new StringBuilder();
        double[][][] QTable;
        if(player == 0) QTable = QTableFor;
        else QTable = QTableAg;
        for (double[][] doubles : QTable) {
            QTableOutput.append("[");
                for (int j = 0; j < QTable[0].length; j++) {
                    QTableOutput.append("[");
                    for(int k = 0; k < QTable[0][0].length; k++){
                        QTableOutput.append(" ").append(df.format(doubles[j][k])).append(" ");
                    }
                    QTableOutput.append("]");
                }
            QTableOutput.append("]").append("\n");
        }
        return QTableOutput.toString();
    }

    public double averageReward(int player){
        double avgReward = 0;
        double[][][] QTable;
        if(player == 0) QTable = QTableFor;
        else QTable = QTableAg;
        for (double[][] doubles : QTable) {
            for (int j = 0; j < QTable[0].length; j++) {
                for(int k = 0; k < QTable[0][0].length; k++){
                    avgReward = avgReward + doubles[j][k];
                }
            }
        }
        return avgReward;
    }

}
