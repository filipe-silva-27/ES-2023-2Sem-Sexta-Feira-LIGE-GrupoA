package gui;

import controllers.*;
import models.Schedule;
import views.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.*;

/**
 * Classe que e´ a app principal que interage com o utilizador - GUI
 */
public class App {

    private final JFrame frame;
    private final JPanel mainPanel;
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    /**
     * Variáveis estáticas
     */
    public static final String MAIN_MENU ="mainMenuView";
    public static final String UPLOAD_MENU ="uploadFilesView";
    public static final String SHOW_SCHEDULE_MENU ="showScheduleView";
    public static final String CONVERT_MENU = "convertView";
    public static final String CREATE_SCHEDULE_MENU ="createScheduleView";

    /**
     * Método construtor
     */
    public App() {
        logger.info("Inicializando a GUI...");
        // Inicializar a frame e o painel principal
        this.frame = new JFrame("Calendar App");
        CardLayout cardLayout = new CardLayout();
        this.mainPanel = new JPanel();

        mainPanel.setLayout(cardLayout);

        initViews();

        // Configurar a frame
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Mostrar o menu de upload de ficheiros/horario
        cardLayout.show(mainPanel, UPLOAD_MENU);
        logger.info("GUI inicializada com sucesso!");
    }

    /**
     * Função que inicializa as views e adiciona ao painel
     */
    private void initViews(){
        logger.info("Inicializando as views...");
        // Inicializar as views
        MainMenuView mainMenuView = new MainMenuView(new MainMenuController(this));
        UploadFilesView uploadFilesView = new UploadFilesView(new UploadFilesController(this));
        CreateScheduleView createScheduleView = new CreateScheduleView(new CreateScheduleController(this));
        ShowScheduleView showScheduleView = new ShowScheduleView( new ShowScheduleController(this));
        ConvertFilesView convertFilesView = new ConvertFilesView(new ConvertController(this));

        // Adicionar as views ao CardLayout
        mainPanel.add(mainMenuView, MAIN_MENU);
        mainPanel.add(uploadFilesView, UPLOAD_MENU);
        mainPanel.add(createScheduleView, CREATE_SCHEDULE_MENU);
        mainPanel.add(showScheduleView, SHOW_SCHEDULE_MENU);
        mainPanel.add(convertFilesView, CONVERT_MENU);
        logger.info("Views inicializadas");
    }

    public static void main(String[] args) {
        new App();
    }

    /**
     * Elementos GUI
     */
    public JFrame getFrame() {
        return frame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setSchedule(Schedule schedule) {
    }
}