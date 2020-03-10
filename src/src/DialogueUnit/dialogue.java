package DialogueUnit;

import CommitmentUnit.commitment;
import KBUnit.knowledgeBase;
import MainUnit.agent;
import MainUnit.main;

import java.util.Random;

public class dialogue {

    public void argue(agent turn, String argument){
        commitment cm = new commitment();
        cm.writeCommit(turn,argument);
        main.getInstance().UI.setChatbox(main.getInstance().ag.currentTurn().toString() + ": " + argument);
    }

    public void runArgument(){
        knowledgeBase kb = new knowledgeBase();
        Random rand = new Random();
        for(int i = 0; i < 3; i++){
            argue(main.getInstance().ag.currentTurn(),kb.readKB().get(rand.nextInt(2)));
            main.getInstance().ag.nextTurn();
        }
    }

}
