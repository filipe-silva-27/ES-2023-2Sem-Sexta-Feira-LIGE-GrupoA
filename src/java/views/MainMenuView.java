package views;

import gui.ViewController;

import javax.swing.JButton;

public class MainMenuView extends View {

    public MainMenuView(ViewController viewController) {
        super(viewController);

    }

    @Override
    public void initFrame() {
        JButton criarBtn = new JButton("Criar Horário");
        JButton convertToJSONBtn = new JButton("Converter CSV para JSON");
        JButton convertToCSVBtn = new JButton("Converter JSON para CSV");
        JButton viewBtn = new JButton("Visualizar Horário");

        criarBtn.addActionListener(e -> viewController.showCreateScheduleView());
        convertToJSONBtn.addActionListener(e -> viewController.convertCSVtoJSON());
        convertToCSVBtn.addActionListener(e -> viewController.convertJSONtoCSV());
        viewBtn.addActionListener(e -> viewController.showShowScheduleView());

        add(criarBtn);
        add(convertToJSONBtn);
        add(convertToCSVBtn);
        add(viewBtn);
    }
}

