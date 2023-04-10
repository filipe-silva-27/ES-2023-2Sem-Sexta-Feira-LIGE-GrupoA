package views;

import gui.ViewController;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ConvertFilesView extends JPanel {
    private final ViewController viewController;

    public ConvertFilesView(ViewController viewController) {
        this.viewController = viewController;

        // Cria os botões e adiciona-os ao painel
        JButton convertToJsonBtn = new JButton("Converter para JSON");
        JButton convertToCSVBtn = new JButton("Converter para CSV");


        // Create a button for the view
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> viewController.showMainMenuView());
        convertToJsonBtn.addActionListener(e -> viewController.showUploadFilesView());



        // Add the button to the panel
        add(backBtn);
        add(convertToJsonBtn);
        add(convertToCSVBtn);
    }
}
