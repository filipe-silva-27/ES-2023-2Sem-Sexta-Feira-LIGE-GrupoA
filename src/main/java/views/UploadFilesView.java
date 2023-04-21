package views;

import controllers.UploadFilesController;
import controllers.ViewController;

import javax.swing.*;

/**
 * Classe que representa a view de upload de arquivos.
 */
public class UploadFilesView extends View {

    /**
     * Construtor da classe UploadFilesView.
     * @param viewController O controlador da view.
     */
    public UploadFilesView(ViewController viewController) {
        super(viewController);
    }

    /**
     * Inicializa o frame da view.
     */
    @Override
    public void initFrame() {
        this.removeAll();
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
