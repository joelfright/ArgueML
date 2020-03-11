package KBUnit;

import MainUnit.base;

import java.util.ArrayList;

public class knowledgeBase {

    public ArrayList<String> readKB(){
        return base.instance.csv.readCSV("csv Files/knowledgeBase.csv");
    }

    public void argumentStructure(){

    }

}
