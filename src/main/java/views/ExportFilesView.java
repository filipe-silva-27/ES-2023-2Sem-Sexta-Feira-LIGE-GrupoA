package views;

import controllers.ExportController;
import controllers.ViewController;
import org.junit.Ignore;

import javax.swing.*;

/**
 * Classe que representa a view para a exportação de arquivos.
 * @see View
 */
public class ExportFilesView extends View {

    /**
     * Construtor da classe ExportFilesView.
     * @param viewController O controlador da view.
     * @see ViewController
     */
    public ExportFilesView(ViewController viewController){
        super(viewController);
    }

    /**
     * Inicializa o frame da view.
     */
    @Override
    @Ignore
    public void initFrame() {
        this.removeAll();

        JButton exportLocal = new JButton("Exportar para o Computador");
        JButton exportRemote = new JButton("Exportar para a Nuvem GitHub Gist");

        exportLocal.addActionListener(e -> ((ExportController) viewController).exportToLocal());
        exportRemote.addActionListener(e -> ((ExportController) viewController).exportToRemote());

        add(exportLocal);
        add(exportRemote);

        //buscar controller da Main Menu atraves da app do viewController
        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());
        add(backBtn);
    }

}
