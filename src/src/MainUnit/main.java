package MainUnit;

import InterfaceUnit.userInterface;

public class main {

    private static main instance;
    public userInterface UI;
    public agent ag;

    private main(){
        UI = new userInterface();
        ag = agent.A;
    }

    public static main getInstance() {
        if (instance == null) {
            instance = new main();
        }
        return instance;
    }

    public static void main(String[] args){
        main.getInstance().boot();
        main.getInstance().run();
    }

    public void boot(){
        UI.mainWindow();
        ag.reset();
    }

    public void run(){

    }

}
