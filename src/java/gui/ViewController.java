package gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Classe ViewController que é o controlador das views todas que
 * trata da lógica dos botões etc
 */
public abstract class ViewController {

    protected final App app;
    protected final CardLayout cardLayout;

    protected final JFrame frame;
    protected final JPanel contentPane;

    /**
     * Construtor do controlador
     */
    protected ViewController(App app) {
        this.cardLayout = (CardLayout) app.getMainPanel().getLayout();
        this.contentPane = app.getMainPanel();
        this.frame = app.getFrame();
        this.app = app;
    }

    public abstract void showView();

    /**
     * Método que verifica se o ficheiro/horário foi uploaded
     * @return boolean - se o ficheiro foi uploaded ou não
     */
    public boolean isFileUploaded(){
        if ( app.getSchedule() != null){
            return app.getSchedule() != null;

        }else {
            throw new NullPointerException("Schedule is null");
        }
    }

    public App getApp() {
        return app;
    }

    public void exportScheduleToCSV(){
        if(isFileUploaded()){
            File f = app.getSchedule().getFile();
        }
    }
}
