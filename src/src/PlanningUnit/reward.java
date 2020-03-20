package PlanningUnit;

import MainUnit.base;

import java.util.ArrayList;

public class reward {

    int[] getReward(){
        ArrayList<String[]> input = base.instance.csv.readCSV("src/csv Files/reward.csv");
        int[] reward = new int[input.size()];
        for(int i = 0; i < input.size(); i++){
            reward[i] = Integer.parseInt(input.get(i)[1]);
        }
        return reward;
    }

}
