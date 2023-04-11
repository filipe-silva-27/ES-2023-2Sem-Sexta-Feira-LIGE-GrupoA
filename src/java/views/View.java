package views;

import gui.ViewController;

import javax.swing.*;

/**
 * Classe abstrata das views
 */
public abstract class View extends JPanel {

    protected final ViewController viewController;

    /**
     * Construtor da view que inicializa a frame (pintar os componentes)
     * @param viewController ViewController que é o controlador das views
     */
    protected View(ViewController viewController){
        this.viewController = viewController;
        initFrame();
    }

    public abstract void initFrame();



}
