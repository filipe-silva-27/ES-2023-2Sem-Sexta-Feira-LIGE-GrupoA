package gui;

import javax.swing.*;
import java.awt.*;

public abstract class Screen {

    final GUI gui;
    final JFrame frame;
    final Container contentPanel;

    public Screen(String title) {
        gui = GUI.getInstance();
        frame = gui.getFrame();
        frame.setName(title);
        contentPanel = gui.getContentPanel();
        this.init();
    }

    public abstract void init();

    /*public abstract void back();

    public abstract void next();

    public abstract void exit();*/
}
