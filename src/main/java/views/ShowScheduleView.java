package views;

import controllers.ShowScheduleController;
import controllers.ViewController;
import models.Aula;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Classe que representa a view de visualização do horário.
 */
public class ShowScheduleView extends View {

    private static final Logger logger = LoggerFactory.getLogger(ShowScheduleView.class);

    /**
     * Construtor da classe ShowScheduleView.
     * @param viewController O controlador da view.
     */
    public ShowScheduleView(ViewController viewController) {
        super(viewController);
    }

    private void showSobreposicoesView(){


        List<Aula> aulasSobrepostas = ((ShowScheduleController) viewController).showSobreposicoes();

        if (!aulasSobrepostas.isEmpty()) {
            showSobrepostasDialog(aulasSobrepostas);
        } else {
            JOptionPane.showMessageDialog(null, "Não existem aulas sobrepostas!",
                    "Aulas sobrepostas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showSobrepostasDialog(List<Aula> aulasSobrepostas){
        JDialog dialog = new JDialog();
        dialog.setTitle("Aulas sobrepostas");
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Data", "Hora", "Unidade Curricular", "Turno"};

        Object[][] data = new Object[aulasSobrepostas.size()][4];
        for (int i = 0; i < aulasSobrepostas.size(); i++) {
            Aula aula = aulasSobrepostas.get(i);
            String[] dataApresentavel = aula.getDataAula().getData().toString().split(" ");
            String dataAula = (dataApresentavel[0] + " " + dataApresentavel[1] + " " + dataApresentavel[2] + " " + dataApresentavel[5]);
            data[i][0] = dataAula;
            data[i][1] = aula.getDataAula().getHoraInicio() + " - " + aula.getDataAula().getHoraFim();
            data[i][2] = aula.getUc().getNomeUC();
            data[i][3] = aula.getTurno();
        }

        JTable table = new JTable(data, columnNames);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        dialog.getContentPane().add(scrollPane);

        int sobrepostas = aulasSobrepostas.size();

        JLabel percentLabel = new JLabel("NºAulas sobrepostas: " + sobrepostas);
        percentLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        dialog.getContentPane().add(percentLabel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }



    /**
     * Inicializa o frame da view.
     */
    @Override
    public void initFrame() {

        this.removeAll();

        JButton verAulas = new JButton("Ver aulas");

        verAulas.addActionListener(e ->
                ShowScheduleController.createHtmlView(((ShowScheduleController) viewController).getAulas()));

        JButton verSobreposicoes = new JButton("Ver sobreposições");
        verSobreposicoes.addActionListener(e ->showSobreposicoesView());

        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());

        add(verAulas);
        add(verSobreposicoes);
        add(backBtn);

    }
}
