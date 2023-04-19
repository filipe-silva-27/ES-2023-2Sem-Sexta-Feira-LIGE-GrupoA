package views;

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

        //TODO - a "conversão" corresponde à exportação do objeto Horário

        //TODO - Botão converter para JSON só é mostrado caso a extensao do ficheiro seja CSV
        //convertToJSONBtn.addActionListener(e -> ((ConvertController) viewController).convertCSVtoJSON());
        //TODO - Botão converter para CSV só é mostrado caso a extensao do ficheiro seja JSON
        // convertToCSVBtn.addActionListener(e -> ((ConvertController) viewController).convertJSONtoCSV());

        add(convertToJSONBtn);
        add(convertToCSVBtn);

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
