package views;

import gui.ViewController;

import javax.swing.*;

import static gui.App.*;

public class ShowScheduleView extends View{

    public ShowScheduleView(ViewController viewController) {
        super(viewController);
    }

    @Override
    public void initFrame() {
        //TODO implementar a visualização do horário

        String[] columnNames = {"Horário", "Segunda", "Terça", "Quarta", "Quinta", "Sexta"};

        Object[][] data = {
                {"08:00 - 09:00", "", "", "", "", ""},
                {"09:00 - 10:00", "", "", "", "", ""},
                {"10:00 - 11:00", "", "", "", "", ""},
                {"11:00 - 12:00", "", "", "", "", ""},
                {"12:00 - 13:00", "", "", "", "", ""},
                {"13:00 - 14:00", "", "", "", "", ""},
                {"14:00 - 15:00", "", "", "", "", ""},
                {"15:00 - 16:00", "", "", "", "", ""},
                {"16:00 - 17:00", "", "", "", "", ""},
                {"17:00 - 18:00", "", "", "", "", ""},
                {"18:00 - 19:00", "", "", "", "", ""},
                {"19:00 - 20:00", "", "", "", "", ""},
                {"20:00 - 21:00", "", "", "", "", ""},
                {"21:00 - 22:00", "", "", "", "", ""}
        };

        JTable jTable = new JTable(data, columnNames);
        JScrollPane jScrollPane = new JScrollPane(jTable);

        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.getApp().getControllers().get(MAIN_MENU).showView());

        //add botao para exportar
        add(jScrollPane);
        add(backBtn);
    }



}
