package views;

import gui.UploadFilesController;
import gui.ViewController;

import javax.swing.*;

public class UploadFilesView extends View {

    public UploadFilesView(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void initFrame() {
        // Cria os botões e adiciona-os ao painel
        JButton localBtn = new JButton("Ficheiro local");
        JButton remoteBtn = new JButton("Ficheiro online");

        localBtn.addActionListener(e -> ((UploadFilesController) viewController).importLocalFile());
        remoteBtn.addActionListener(e -> ((UploadFilesController) viewController).importRemoteFile());

        // Add the buttons to the panel
        add(localBtn);
        add(remoteBtn);
    }

}
