package KBUnit;

import DataHandling.csvHandler;
import java.util.ArrayList;

public class knowledgeBase {

    public ArrayList<String> readKB(){
        csvHandler csv = new csvHandler();
        return csv.readCSV("csv Files/knowledgeBase.csv");
    }

}
