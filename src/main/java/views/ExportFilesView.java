package views;

import controllers.ExportController;
import controllers.ViewController;

import javax.swing.*;

public class ExportFilesView extends View {

    public ExportFilesView(ViewController viewController){
        super(viewController);
    }

    @Override
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
