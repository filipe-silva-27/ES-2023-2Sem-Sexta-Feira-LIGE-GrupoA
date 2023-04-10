package views;

import gui.ViewController;

import javax.swing.*;
import java.io.IOException;

public class UploadFilesView extends JPanel {
    private final ViewController viewController;

    public UploadFilesView(ViewController viewController) {
        this.viewController = viewController;

        // Cria os botões e adiciona-os ao painel
        JButton localBtn = new JButton("Ficheiro local");
        JButton remoteBtn = new JButton("Ficheiro online");


        // Create a button for the view
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> viewController.showMainMenuView());
        localBtn.addActionListener(e -> viewController.importLocalFile());
        remoteBtn.addActionListener(e -> viewController.importRemoteFile());



        // Add the button to the panel
        add(backBtn);
        add(localBtn);
        add(remoteBtn);
    }



}
