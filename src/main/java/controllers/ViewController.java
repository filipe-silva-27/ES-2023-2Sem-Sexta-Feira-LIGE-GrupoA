package controllers;

import gui.App;
import models.Horario;


import javax.swing.*;
import java.awt.*;


/**
 * Classe ViewController que é o controlador das views todas que
 * trata da lógica dos botões etc
 * @see App
 */
public class ViewController {

    protected final App app;
    protected final CardLayout cardLayout;

    protected final JFrame frame;
    protected final JPanel contentPane;
    private static String content;
    private static Horario horario = null;

    private static boolean isURI = false;
    /**
     * Construtor do controlador
     * @param app A instância da classe App
     * @see App
     */
    public ViewController(App app) {
        this.cardLayout = (CardLayout) app.getMainPanel().getLayout();
        this.contentPane = app.getMainPanel();
        this.frame = app.getFrame();
        this.app = app;
    }


    /**
     * Obtém o conteúdo atual
     * @return O conteúdo atual
     */
    public static String getContent() {
        return content;
    }

    /**
     * Verifica se o conteúdo está definido
     * @return true se o conteúdo está definido, false caso contrário
     */
    public boolean isContentSet(){
        return content!=null;
    }

    /**
     * Define o conteúdo
     * @param content O conteúdo a ser definido
     */
    public static void setContent(final String content) {
        ViewController.content = content;
    }

    /**
     * Verifica se um arquivo foi carregado
     * @return true se um arquivo foi carregado, false caso contrário
     */
    public boolean isFileUploaded(){
        return getHorario().getFile() != null;
    }

    /**
     * Verifica se um horário foi definido
     * @return true se um horário foi definido, false caso contrário
     */
    public boolean isHorarioSet(){
        return getHorario() != null;
    }

    /**
     * Obtém a instância da classe App
     * @return A instância da classe App
     */
    public App getApp() {
        return app;
    }

    /**
     * Obtém o horário atual
     * @return O horário atual
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * Define o horário
     * @param horario O horário a ser definido
     */
    public static void setHorario(Horario horario) {
        ViewController.horario = horario;
    }

    /**
     * Métodos que mostram as views
     */

    /**
     * Mostra a view de conversão
     */
    public void showConvertView(){
        app.getConvertFilesView().initFrame();
        cardLayout.show(contentPane, App.CONVERT_MENU);
    }

    /**
     * Mostra a view de criação de horário
     */
    public void showCreateScheduleView(){
        app.getCreateScheduleView().initFrame();
        cardLayout.show(contentPane, App.CREATE_SCHEDULE_MENU);
    }

    /**
     * Mostra a view do menu principal
     */
    public void showMainMenuView(){
        if(isHorarioSet()){
            app.getMainMenuView().initFrame();
            cardLayout.show(contentPane, App.MAIN_MENU);
        }else {
            app.getImportFilesView().initFrame();
            cardLayout.show(contentPane, App.UPLOAD_MENU);
        }
    }

    /**
     * Mostra a view de exibição do horário
     */
    public void showShowScheduleView(){
        if(isHorarioSet()){
            app.getShowScheduleView().initFrame();
            cardLayout.show(contentPane, App.SHOW_SCHEDULE_MENU);
        }else {
            app.getImportFilesView().initFrame();
            cardLayout.show(contentPane, App.UPLOAD_MENU);
        }
    }


    /**
     * Exibe a tela de upload de ficheiros.
     */
    public void showImportFilesView(){
        setIsURI(false);
        app.getImportFilesView().initFrame();
        cardLayout.show(contentPane, App.UPLOAD_MENU);
    }

    /**
     * Exibe a tela de exportação de ficheiros.
     */
    public void showExportFilesView(){
        app.getExportFilesView().initFrame();
        cardLayout.show(contentPane, App.EXPORT_MENU);
    }

    /**
     * Retorna o objeto CardLayout utilizado no componente.
     * @return o objeto CardLayout utilizado no componente
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public boolean isIsURI() {
        return isURI;
    }

    public void setIsURI(boolean isURI) {
        ViewController.isURI = isURI;
    }
}
