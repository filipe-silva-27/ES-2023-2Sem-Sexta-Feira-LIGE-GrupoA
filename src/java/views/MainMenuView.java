package views;

import gui.ViewController;

import javax.swing.JButton;

public class MainMenuView extends View {

    public MainMenuView(ViewController viewController) {
        super(viewController);

    }

    @Override
    public void initFrame() {
        JButton criarBtn = new JButton("Criar Horário");
        JButton viewBtn = new JButton("Visualizar Horário");
        JButton convertBtn = new JButton("Converter");

        System.out.println(viewController.getClass());
        criarBtn.addActionListener(e -> viewController.showCreateScheduleView());
        viewBtn.addActionListener(e -> viewController.showShowScheduleView());
        convertBtn.addActionListener(e -> viewController.showConvertView());

        //back button to redirect to UploadFilesView
        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showUploadFilesView());


        add(criarBtn);
        add(viewBtn);
        add(convertBtn);
        add(backBtn);
    }
}

