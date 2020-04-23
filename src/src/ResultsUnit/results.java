package ResultsUnit;

import MainUnit.base;

public class results {

    int iteration = 0;

    public void writeResults(int player, double learningRate){
        iteration++;
        double avgPlayer = base.instance.ql.averageReward(player);
        base.instance.csv.writeCSV("src/csv Files/results/resultsP" + (player + 1) + " - " + learningRate + ".csv",Double.toString(avgPlayer),iteration);
    }

}
