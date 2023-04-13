package gui;

import static gui.App.UPLOAD_MENU;

public class MainMenuController extends ViewController{

    public MainMenuController(App app) {
        super(app);
    }

    public void showView(){
        if(isFileUploaded()){
                   cardLayout.show(contentPane, App.MAIN_MENU);
               }else {
                   app.getControllers().get(UPLOAD_MENU).showView();
               }
    }

}
