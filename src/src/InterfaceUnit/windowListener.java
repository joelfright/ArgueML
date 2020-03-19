package InterfaceUnit;

import MainUnit.base;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class windowListener implements WindowListener {

    @Override
    public void windowOpened(WindowEvent e) {

    }

    public void windowClosing(WindowEvent arg){
        base.instance.cm.clearCommit();
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}
