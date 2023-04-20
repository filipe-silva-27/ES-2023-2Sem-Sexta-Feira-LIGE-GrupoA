package controllers;

import gui.App;
import models.Horario;


import javax.swing.*;
import java.awt.*;


/**
 * Classe ViewController que é o controlador das views todas que
 * trata da lógica dos botões etc
 */
public class ViewController {

    protected final App app;
    protected final CardLayout cardLayout;

    protected final JFrame frame;
    protected final JPanel contentPane;
    //fazer string content
    private static String content;
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

    public static String getContent() {
        return content;
    }

    public boolean isContentSet(){
        return content!=null;
    }

    public static void setContent(final String content) {
        ViewController.content = content;
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

    public Horario getHorario() {
        return horario;
    }

    public static void setHorario(Horario horario) {
        ViewController.horario = horario;
    }

    /**
     * Métodos que mostram as views
     */

    public void showConvertView(){
        app.getConvertFilesView().initFrame();
        cardLayout.show(contentPane, App.CONVERT_MENU);
    }

    public void showCreateScheduleView(){
        app.getCreateScheduleView().initFrame();
        cardLayout.show(contentPane, App.CREATE_SCHEDULE_MENU);
    }

    public void showMainMenuView(){
        if(isHorarioSet()){
            app.getMainMenuView().initFrame();
            cardLayout.show(contentPane, App.MAIN_MENU);
        }else {
            app.getUploadFilesView().initFrame();
            cardLayout.show(contentPane, App.UPLOAD_MENU);
        }
    }

    public void showShowScheduleView(){
        if(isHorarioSet()){
            app.getShowScheduleView().initFrame();
            cardLayout.show(contentPane, App.SHOW_SCHEDULE_MENU);
        }else {
            app.getUploadFilesView().initFrame();
            cardLayout.show(contentPane, App.UPLOAD_MENU);
        }
    }

    public void showUploadFilesView(){
        app.getUploadFilesView().initFrame();
        cardLayout.show(contentPane, App.UPLOAD_MENU);
    }

    //show ExportFilesView
    public void showExportFilesView(){
        app.getExportFilesView().initFrame();
        cardLayout.show(contentPane, App.EXPORT_MENU);
    }


    public CardLayout getCardLayout() {
        return cardLayout;
    }
}
