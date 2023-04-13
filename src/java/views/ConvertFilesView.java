package views;

import gui.ConvertController;
import gui.ViewController;

import javax.swing.*;

import static gui.App.*;

public class ConvertFilesView extends View{

    public ConvertFilesView(ViewController viewController){
        super(viewController);
    }

    @Override
    public void initFrame() {
        //TODO implementar criação do horário

        JButton convertToJSONBtn = new JButton("Converter CSV para JSON");
        JButton convertToCSVBtn = new JButton("Converter JSON para CSV");

        convertToJSONBtn.addActionListener(e -> ((ConvertController) viewController).convertCSVtoJSON());
        convertToCSVBtn.addActionListener(e -> ((ConvertController) viewController).convertJSONtoCSV());

        add(convertToJSONBtn);
        add(convertToCSVBtn);

        //buscar controller da Main Menu atraves da app do viewController
        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.getApp().getControllers().get(MAIN_MENU).showView());

        add(backBtn);

    }
}
