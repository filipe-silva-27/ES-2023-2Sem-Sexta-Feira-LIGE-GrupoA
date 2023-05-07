package views;


import controllers.ViewController;

import javax.swing.*;
import java.net.URISyntaxException;

/**
 * Classe abstrata das views que extende o JPanel do swing
 * @see JPanel
 */
public abstract class View extends JPanel {

    protected transient ViewController viewController;

    /**
     * Construtor da view que inicializa a frame (pintar os componentes)
     * @param viewController ViewController que é o controlador das views
     * @see ViewController
     */
    protected View(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Inicializa o frame da view.
     */
    public abstract void initFrame() throws URISyntaxException;


    /**
     * Define o controlador da view.
     * @param viewController O controlador das views.
     */
    public void setController(ViewController viewController) {
        this.viewController=viewController;
    }
}
