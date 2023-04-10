package views;

import gui.ViewController;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainMenuView extends JPanel {
    private final ViewController viewController;
    private final JButton criarBtn;
    private final JButton convertBtn;
    private final JButton visualizarBtn;

    public MainMenuView(ViewController viewController) {
        this.viewController = viewController;

        criarBtn = new JButton("Criar Horário");
        convertBtn = new JButton("Converter Ficheiros");
        visualizarBtn = new JButton("Visualizar Horário");

        convertBtn.addActionListener(e -> viewController.showConvertFilesView());

        add(criarBtn);
        add(convertBtn);
        add(visualizarBtn);
    }
}

