package views;

import controllers.ImportFilesController;
import controllers.ViewController;
import org.junit.Ignore;

import javax.swing.*;

/**
 * Classe que representa a view de upload de arquivos.
 */
public class ImportFilesView extends View {

    /**
     * Construtor da classe ImportFilesView.
     * @param viewController O controlador da view.
     */
    public ImportFilesView(ViewController viewController) {
        super(viewController);
    }

    /**
     * Inicializa o frame da view.
     */
    @Override
    @Ignore
    public void initFrame() {
        this.removeAll();
        // Cria os botões e adiciona-os ao painel
        JButton localBtn = new JButton("Ficheiro local");
        JButton remoteBtn = new JButton("Ficheiro online");

        localBtn.addActionListener(e -> ((ImportFilesController) viewController).importLocalFile());
        remoteBtn.addActionListener(e -> ((ImportFilesController) viewController).importRemoteFile());

        add(localBtn);
        add(remoteBtn);
    }

}
