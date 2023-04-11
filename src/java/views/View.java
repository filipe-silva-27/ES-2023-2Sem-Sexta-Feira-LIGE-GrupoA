package views;

import gui.ViewController;

import javax.swing.*;

public abstract class View extends JPanel {

    protected final ViewController viewController;

    protected View(ViewController viewController){
        this.viewController = viewController;
        initFrame();
    }

    public abstract void initFrame();



}
