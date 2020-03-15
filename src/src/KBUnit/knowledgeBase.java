package KBUnit;

import MainUnit.agent;
import MainUnit.base;

import java.util.ArrayList;

public class knowledgeBase {

    private ArrayList<String[]> readKB(){
        return base.instance.csv.readCSV("csv Files/knowledgeBase.csv");
    }

    public ArrayList<String[]> propositions(Enum agen){
        String option;
        if(agen == agent.A){ option = "FOR";}else{option = "AGAINST";}
        ArrayList<String[]> inputs = readKB();
        ArrayList<String[]> outputs = new ArrayList<>();
        for (String[] input : inputs) {
            if (input[1].equals(option)) {
                outputs.add(input);
            }
        }
        return outputs;
    }

}
