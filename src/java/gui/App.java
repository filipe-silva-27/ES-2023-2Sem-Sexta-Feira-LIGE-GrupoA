package gui;

import models.Schedule;
import views.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 * Classe que e´ a app principal que interage com o utilizador - GUI
 */
public class App {

    private final JFrame frame;
    private final JPanel mainPanel;
    private Schedule schedule;

    private Map<String, ViewController> controllers;

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
        // Inicializar a frame e o painel principal
        this.frame = new JFrame("Calendar App");
        CardLayout cardLayout = new CardLayout();
        this.mainPanel = new JPanel();
        this.controllers = new HashMap<>();

        getMainPanel().setLayout(cardLayout);

        initControllers();
        initViews();

        // Configurar a frame
        getFrame().getContentPane().add(getMainPanel(), BorderLayout.CENTER);
        getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getFrame().pack();
        getFrame().setVisible(true);

        // Mostrar o menu de upload de ficheiros/horario
        controllers.get(UPLOAD_MENU).showView();
        schedule = null;
    }

    /**
     * Método que inicializa os controllers da GUI (MVC)
     */
    private void initControllers(){
        //initialize each class of controllers
        MainMenuController mainMenuController = new MainMenuController(this);
        UploadFilesController uploadFilesController = new UploadFilesController(this);
        CreateScheduleController createScheduleController = new CreateScheduleController(this);
        ShowScheduleController showScheduleController = new ShowScheduleController(this);
        ConvertController convertController = new ConvertController(this);

        //add each controller to the list
        getControllers().put(MAIN_MENU, mainMenuController);
        getControllers().put(UPLOAD_MENU ,uploadFilesController);
        getControllers().put(CREATE_SCHEDULE_MENU,createScheduleController);
        getControllers().put(SHOW_SCHEDULE_MENU,showScheduleController);
        getControllers().put(CONVERT_MENU,convertController);


    }

    /**
     * Função que inicializa as views e adiciona ao painel
     */
    private void initViews(){

        // Inicializar as views
        MainMenuView mainMenuView = new MainMenuView(getControllers().get(MAIN_MENU));
        UploadFilesView uploadFilesView = new UploadFilesView(getControllers().get(UPLOAD_MENU));
        CreateScheduleView createScheduleView = new CreateScheduleView(getControllers().get(CREATE_SCHEDULE_MENU));
        ShowScheduleView showScheduleView = new ShowScheduleView(getControllers().get(SHOW_SCHEDULE_MENU));
        ConvertFilesView convertFilesView = new ConvertFilesView(getControllers().get(CONVERT_MENU));

        // Adicionar as views ao CardLayout
        getMainPanel().add(mainMenuView, MAIN_MENU);
        getMainPanel().add(uploadFilesView, UPLOAD_MENU);
        getMainPanel().add(createScheduleView, CREATE_SCHEDULE_MENU);
        getMainPanel().add(showScheduleView, SHOW_SCHEDULE_MENU);
        getMainPanel().add(convertFilesView, CONVERT_MENU);

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
     * Objetos Controller
     */
    public Map<String, ViewController> getControllers() {
        return controllers;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(final Schedule schedule) {
        this.schedule = schedule;
    }
}
