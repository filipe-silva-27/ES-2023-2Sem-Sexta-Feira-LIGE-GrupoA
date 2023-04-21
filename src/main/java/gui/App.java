package gui;

import controllers.*;
import views.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.*;

/**
 * Classe que é a aplicação principal que interage com o utilizador - GUI.
 * Esta classe cria uma interface gráfica do usuário (GUI) para a aplicação
 * Calendar App, que permite ao usuário interagir com várias funcionalidades,
 * como upload de arquivos, visualização de horários, criação de horários,
 * conversão de arquivos e exportação de arquivos.
 */
public class App {

    private final JFrame frame;
    private final JPanel mainPanel;
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    /**
     * Variáveis estáticas que representam os nomes dos painéis no CardLayout.
     * Esses nomes são usados para trocar entre as diferentes telas da aplicação.
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
     * Método construtor que inicializa a GUI da aplicação.
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
        getUploadFilesView().initFrame();
        cardLayout.show(mainPanel, UPLOAD_MENU);
        logger.info("GUI inicializada com sucesso!");
    }

    /**
     * Função que inicializa as views e adiciona ao painel.
     * Cada view é inicializada com seu respectivo controller e adicionada ao CardLayout.
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
     * Obtém a referência para a JFrame da aplicação.
     * @return A JFrame da aplicação.
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Obtém a referência para o painel principal da aplicação.
     * @return O JPanel do painel principal da aplicação.
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Obtém a referência para a view do menu principal.
     * @return A MainMenuView do menu principal.
     */
    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }

    /**
     * Obtém a referência para a view de upload de arquivos.
     * @return A UploadFilesView da view de upload de arquivos.
     */
    public UploadFilesView getUploadFilesView() {
        return uploadFilesView;
    }

    /**
     * Obtém a referência para a view de exibição de horário.
     * @return A ShowScheduleView da view de exibição de horário.
     */
    public ShowScheduleView getShowScheduleView() {
        return showScheduleView;
    }

    /**
     * Obtém a referência para a view de criação de horário.
     * @return A CreateScheduleView da view de criação de horário.
     */
    public CreateScheduleView getCreateScheduleView() {
        return createScheduleView;
    }

    /**
     * Obtém a referência para a view de conversão de arquivos.
     * @return A ConvertFilesView da view de conversão de arquivos.
     */
    public ConvertFilesView getConvertFilesView() {
        return convertFilesView;
    }

    /**
     * Obtém a referência para a view de exportação de arquivos.
     * @return A ExportFilesView da view de exportação de arquivos.
     */
    public View getExportFilesView() { return exportFilesView;
    }
}
