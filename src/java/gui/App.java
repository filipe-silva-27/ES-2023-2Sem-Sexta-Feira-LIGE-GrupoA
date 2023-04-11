package gui;

import views.CreateScheduleView;
import views.MainMenuView;
import views.ShowScheduleView;
import views.UploadFilesView;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe que e´ a app principal que interage com o utilizador - GUI
 */
public class App {

    /**
     * Elementos GUI
     */
    private final JFrame frame;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;


    /**
     * Objetos View
     */
    private MainMenuView mainMenuView;
    private UploadFilesView uploadFilesView;
    private CreateScheduleView createScheduleView;
    private ShowScheduleView showScheduleView;

    /**
     * Objetos Controller
     */
    private ViewController viewController;

    /**
     * Variáveis estáticas
     */
    public static final String MAIN_MENU_VIEW="mainMenuView";
    public static final String UPLOAD_MENU_VIEW="uploadFilesView";
    public static final String SHOW_SCHEDULE_VIEW="showScheduleView";
    public static final String CREATE_SCHEDULE_VIEW="createScheduleView";

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
        createScheduleView = new CreateScheduleView(viewController);
        showScheduleView = new ShowScheduleView(viewController);

        // Adicionar as views ao CardLayout
        mainPanel.add(mainMenuView, MAIN_MENU_VIEW);
        mainPanel.add(uploadFilesView, UPLOAD_MENU_VIEW);
        mainPanel.add(createScheduleView, CREATE_SCHEDULE_VIEW);
        mainPanel.add(showScheduleView, SHOW_SCHEDULE_VIEW);

    }

    public static void main(String[] args) {
        App app = new App();
    }
}
