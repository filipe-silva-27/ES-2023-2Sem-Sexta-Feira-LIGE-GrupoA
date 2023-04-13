package gui;

public class CreateScheduleController extends ViewController{

    public CreateScheduleController(App app) {
        super(app);
    }

    public void showView(){
        cardLayout.show(contentPane,App.CREATE_SCHEDULE_MENU);
    }
}
