package gui;

public class ShowScheduleController extends ViewController{

    public ShowScheduleController(App app) {
        super(app);
    }

    public void showView(){
        cardLayout.show(contentPane,App.SHOW_SCHEDULE_MENU);
    }
}
