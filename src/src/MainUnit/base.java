package MainUnit;

import CommitmentUnit.commitment;
import DataHandling.csvHandler;
import DialogueUnit.dialogue;
import InterfaceUnit.userInterface;
import KBUnit.knowledgeBase;
import PlanningUnit.planning;

public class base {

    public static base instance = new base();

    public agent ag;
    public userInterface UI = new userInterface();
    public csvHandler csv = new csvHandler();
    public commitment cm = new commitment();
    public dialogue dia = new dialogue();
    public knowledgeBase kb = new knowledgeBase();
    public planning pl = new planning();

    private base(){
        ag = agent.A;
    }

    public static void main(String[] args){
        instance.boot();
    }

    private void boot(){
        UI.mainWindow();
        ag.reset();
    }


}
