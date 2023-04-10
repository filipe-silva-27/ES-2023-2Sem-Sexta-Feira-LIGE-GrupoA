package views;

import gui.ViewController;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenuView extends JPanel {
    private final ViewController viewController;

    public MainMenuView(ViewController viewController) {
        this.viewController = viewController;

        JButton criarBtn = new JButton("Criar Horário");
        JButton convertToJSONBtn = new JButton("Converter CSV para JSON");
        JButton convertToCSVBtn = new JButton("Converter JSON para CSV");
        JButton viewBtn = new JButton("Visualizar Horário");

        criarBtn.addActionListener(e -> viewController.showMainMenuView()); //TODO mudar para criar
        convertToJSONBtn.addActionListener(e -> viewController.convertCSVtoJSON());
        convertToCSVBtn.addActionListener(e -> viewController.convertJSONtoCSV());
        //TODO botao de view

        add(criarBtn);
        add(convertToJSONBtn);
        add(convertToCSVBtn);
        add(viewBtn);
    }
}

