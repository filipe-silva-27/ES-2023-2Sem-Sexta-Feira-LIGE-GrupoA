package views;

import controllers.ConvertController;
import controllers.ViewController;

import javax.swing.*;

/**
 * Classe que representa a visualização para a conversão de arquivos CSV e JSON.
 */
public class ConvertFilesView extends View{

    /**
     * Construtor da classe ConvertFilesView.
     * @param viewController O controlador da view.
     */
    public ConvertFilesView(ViewController viewController){
        super(viewController);
    }

    /**
     * Inicializa o frame da view.
     */
    @Override
    public void initFrame() {
        this.removeAll();

        JButton convertToJSONBtn = new JButton("Converter CSV para JSON");
        JButton convertToCSVBtn = new JButton("Converter JSON para CSV");

        convertToJSONBtn.addActionListener(e -> ((ConvertController) viewController).convertFile());
        convertToCSVBtn.addActionListener(e -> ((ConvertController) viewController).convertFile());

        add(convertToJSONBtn);
        add(convertToCSVBtn);

        if (viewController.getHorario().getFileExtension().equals("csv")) {
            convertToJSONBtn.setVisible(true);
            convertToCSVBtn.setVisible(false);
        } else if (viewController.getHorario().getFileExtension().equals("json")) {
            convertToJSONBtn.setVisible(false);
            convertToCSVBtn.setVisible(true);
        }

        //buscar controller da Main Menu atraves da app do viewController
        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());
        add(backBtn);

    }
}
