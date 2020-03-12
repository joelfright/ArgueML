package CommitmentUnit;

import MainUnit.agent;
import MainUnit.base;

import java.util.ArrayList;

public class commitment {


    public ArrayList<String[]> readCommit(agent ag){
        return base.instance.csv.readCSV("csv Files/commitment" + ag.toString() +".csv");
    }

    public void writeCommit(agent ag, String line, int turn){
        base.instance.csv.writeCSV("csv Files/commitment" + ag.toString() +".csv",line, turn);
    }

    public void clearCommit(){
        base.instance.csv.wipeCSV("csv Files/commitmentA.csv");
        base.instance.csv.wipeCSV("csv Files/commitmentB.csv");
    }

}
