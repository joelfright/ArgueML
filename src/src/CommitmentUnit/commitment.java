package CommitmentUnit;

import DataHandling.csvHandler;
import MainUnit.main;

import java.util.ArrayList;

public class commitment {

    csvHandler csv = new csvHandler();

    public ArrayList<String> readCommit(Enum agent){
        return csv.readCSV("csv Files/commitment" + main.getInstance().ag.toString() +".csv");
    }

    public void writeCommit(Enum agent,String line){
        csv.writeCSV("csv Files/commitment" + main.getInstance().ag.toString() +".csv",line);
    }

    public void clearCommit(){
        csv.wipeCSV("csv Files/commitmentA.csv");
        csv.wipeCSV("csv Files/commitmentB.csv");
    }

}
