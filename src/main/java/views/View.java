package views;

import controllers.ExportController;
import controllers.ViewController;

import javax.swing.*;

/**
 * Classe abstrata das views
 */
public abstract class View extends JPanel {

    protected transient ViewController viewController;

    /**
     * Construtor da view que inicializa a frame (pintar os componentes)
     * @param viewController ViewController que é o controlador das views
     */
    protected View(ViewController viewController){
        this.viewController = viewController;
    }

    public abstract void initFrame();


    public void setController(ViewController viewController) {
        this.viewController=viewController;
    }
}
