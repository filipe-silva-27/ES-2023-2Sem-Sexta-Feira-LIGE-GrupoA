package gui;

import views.MainMenuView;
import views.UploadFilesView;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe que e´ a app principal que interage com o utilizador - GUI
 */
public class App {
    private final JFrame frame;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;
    private MainMenuView mainMenuView;
    private UploadFilesView uploadFilesView;
    private ViewController viewController;

    /**
     * Método construtor
     */
    public App() {
        // Inicializar a frame e o painel principal
        this.frame = new JFrame("Calendar App");
        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel();

        mainPanel.setLayout(cardLayout);

        initControllers();
        initViews();

        // Configurar a frame
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Mostrar o menu de upload de ficheiros/horario
        viewController.showUploadFilesView();
    }

    /**
     * Método que inicializa os controllers da GUI (MVC)
     */
    private void initControllers(){
        viewController = new ViewController(mainPanel, frame);
    }

    /**
     * Função que inicializa as views e adiciona ao painel
     */
    private void initViews(){

        // Inicializar as views
        mainMenuView = new MainMenuView(viewController);
        uploadFilesView = new UploadFilesView(viewController);

        // Adicionar as views ao CardLayout
        mainPanel.add(mainMenuView, "mainMenuView");
        mainPanel.add(uploadFilesView, "uploadFilesView");
    }

    public static void main(String[] args) {
        App app = new App();
    }
}
