package controllers;

import gui.App;
import models.Horario;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 * Classe ViewController que é o controlador das views todas que
 * trata da lógica dos botões etc
 */
public class ViewController {

    protected final App app;
    protected final CardLayout cardLayout;

    protected final JFrame frame;
    protected final JPanel contentPane;
    private static Horario horario = null;

    /**
     * Construtor do controlador
     */
    protected ViewController(App app) {
        this.cardLayout = (CardLayout) app.getMainPanel().getLayout();
        this.contentPane = app.getMainPanel();
        this.frame = app.getFrame();
        this.app = app;
    }

    /**
     * Método que verifica se o ficheiro/horário foi uploaded
     * @return boolean - se o ficheiro foi uploaded ou não
     */
    public boolean isFileUploaded(){
        return getHorario().getFile() != null;
    }

    public boolean isHorarioSet(){
        return getHorario() != null;
    }

    public App getApp() {
        return app;
    }

    //TODO - implementar
    public void exportSchedule(){
        if(isHorarioSet()){
            File fileFrom = getHorario().getFile();
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File fileTo = fileChooser.getSelectedFile();
                try (FileWriter writer = new FileWriter(fileTo)) {
                    // TODO: Code to write export data to the file
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    /**
     * Métodos que mostram as views
     */

    public void showConvertView(){
        cardLayout.show(contentPane, App.CONVERT_MENU);
    }

    public void showCreateScheduleView(){
        cardLayout.show(contentPane, App.CREATE_SCHEDULE_MENU);
    }

    public void showMainMenuView(){
        if(isHorarioSet()){
            cardLayout.show(contentPane, App.MAIN_MENU);
        }else {
            cardLayout.show(contentPane, App.UPLOAD_MENU);
        }
    }

    public void showShowScheduleView(){
        if(isHorarioSet()){
            cardLayout.show(contentPane, App.SHOW_SCHEDULE_MENU);
        }else {
            cardLayout.show(contentPane, App.UPLOAD_MENU);
        }
    }

    public void showUploadFilesView(){
        cardLayout.show(contentPane, App.UPLOAD_MENU);
    }




}
