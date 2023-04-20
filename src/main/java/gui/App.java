package gui;

import com.formdev.flatlaf.FlatLightLaf;
import controllers.*;
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
    public static final String EXPORT_MENU = "exportFilesView";

    private MainMenuView mainMenuView;
    private UploadFilesView uploadFilesView;
    private ShowScheduleView showScheduleView;
    private CreateScheduleView createScheduleView;
    private ConvertFilesView convertFilesView;
    private ExportFilesView exportFilesView;


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

        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
            FlatLightLaf.setup();
        } catch( Exception ex ) {
            logger.error("Falha na inicialização do tema do Swing. Utilizando o tema default." );
        }

        // Configurar a frame
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Mostrar o menu de upload de ficheiros/horario
        getUploadFilesView().initFrame();
        cardLayout.show(mainPanel, UPLOAD_MENU);
        logger.info("GUI inicializada com sucesso!");
    }

    /**
     * Função que inicializa as views e adiciona ao painel
     */
    private void initViews(){
        logger.info("Inicializando as views...");
        // Inicializar as views
        mainMenuView = new MainMenuView(new MainMenuController(this));
        uploadFilesView = new UploadFilesView(new UploadFilesController(this));
        createScheduleView = new CreateScheduleView(new CreateScheduleController(this));
        showScheduleView = new ShowScheduleView( new ShowScheduleController(this));
        convertFilesView = new ConvertFilesView(new ConvertController(this));
        exportFilesView = new ExportFilesView(new ExportController(this));

        // Adicionar as views ao CardLayout
        mainPanel.add(getMainMenuView(), MAIN_MENU);
        mainPanel.add(getUploadFilesView(), UPLOAD_MENU);
        mainPanel.add(getCreateScheduleView(), CREATE_SCHEDULE_MENU);
        mainPanel.add(getShowScheduleView(), SHOW_SCHEDULE_MENU);
        mainPanel.add(getConvertFilesView(), CONVERT_MENU);
        mainPanel.add(getExportFilesView(), EXPORT_MENU);
        logger.info("Views adicionadas com sucesso ao panel!");
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

    /**
     * Views
     */
    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }

    public UploadFilesView getUploadFilesView() {
        return uploadFilesView;
    }

    public ShowScheduleView getShowScheduleView() {
        return showScheduleView;
    }

    public CreateScheduleView getCreateScheduleView() {
        return createScheduleView;
    }

    public ConvertFilesView getConvertFilesView() {
        return convertFilesView;
    }

    public View getExportFilesView() { return exportFilesView;
    }
}
