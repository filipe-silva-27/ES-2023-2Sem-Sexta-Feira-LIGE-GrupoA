package views;

import gui.ViewController;

import javax.swing.JButton;

import static gui.App.*;
import static java.lang.System.out;

public class MainMenuView extends View {

    public MainMenuView(ViewController viewController) {
        super(viewController);

    }

    @Override
    public void initFrame() {
        JButton criarBtn = new JButton("Criar Horário");
        JButton viewBtn = new JButton("Visualizar Horário");
        JButton convertBtn = new JButton("Converter");

        out.println(viewController.getClass());
        criarBtn.addActionListener(e -> viewController.getApp().getControllers().get(CREATE_SCHEDULE_MENU).showView());
        viewBtn.addActionListener(e -> viewController.getApp().getControllers().get(SHOW_SCHEDULE_MENU).showView());
        convertBtn.addActionListener(e -> viewController.getApp().getControllers().get(CONVERT_MENU).showView());

        //back button to redirect to UploadFilesView
        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.getApp().getControllers().get(UPLOAD_MENU).showView());


        add(criarBtn);
        add(viewBtn);
        add(convertBtn);
        add(backBtn);
    }
}

