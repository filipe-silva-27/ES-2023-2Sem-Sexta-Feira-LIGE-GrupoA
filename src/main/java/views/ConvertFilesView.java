package views;

import controllers.ConvertController;
import controllers.ViewController;

import javax.swing.*;

public class ConvertFilesView extends View{

    public ConvertFilesView(ViewController viewController){
        super(viewController);
    }

    @Override
    public void initFrame() {

        JButton convertToJSONBtn = new JButton("Converter CSV para JSON");
        JButton convertToCSVBtn = new JButton("Converter JSON para CSV");

        convertToJSONBtn.addActionListener(e -> ((ConvertController) viewController).convertCSVtoJSON());
        convertToCSVBtn.addActionListener(e -> ((ConvertController) viewController).convertJSONtoCSV());

        add(convertToJSONBtn);
        add(convertToCSVBtn);

        if (viewController.getHorario().getFileExtension().equals("csv")) {
            convertToJSONBtn.setVisible(true);
            convertToCSVBtn.setVisible(false);
        } else if (viewController.getHorario().getFileExtension().equals("json")) {
            convertToJSONBtn.setVisible(false);
            convertToCSVBtn.setVisible(true);
        }
        //botao para exportar
        JButton exportBtn = new JButton("Exportar");
        exportBtn.addActionListener(e -> viewController.exportSchedule());
        add(exportBtn);

        //buscar controller da Main Menu atraves da app do viewController
        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());
        add(backBtn);

    }
}
