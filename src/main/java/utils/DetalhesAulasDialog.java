package utils;

import models.Aula;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DetalhesAulasDialog{

    private DetalhesAulasDialog(){
        throw new IllegalArgumentException("Esta classe é para utils! Não é para ser instanciada!");
    }


    public static void showSobreposicoesView(List<Aula> aulasSobrepostas){
        if (!aulasSobrepostas.isEmpty()) {
            showSobrepostasDialog(aulasSobrepostas);
        } else {
            JOptionPane.showMessageDialog(null, "Não existem aulas sobrepostas!",
                    "Aulas sobrepostas", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void showSobrepostasDialog(List<Aula> aulasSobrepostas){
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


    public static void showAulasSobrelotadasView(List<Aula> aulasSobrelotadas){
        if (!aulasSobrelotadas.isEmpty()) {
            showSobrelotadasDialog(aulasSobrelotadas);
        } else {
            JOptionPane.showMessageDialog(null, "Não existem aulas sobrelotadas!",
                    "Aulas sobrelotadas", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void showSobrelotadasDialog(List<Aula> aulasSobrelotadas){
        JDialog dialog = new JDialog();
        dialog.setTitle("Aulas sobrelotadas");
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Data", "Hora", "Unidade Curricular", "Turno", "Sala", "Lotação", "NºInscritos"};

        Object[][] data = new Object[aulasSobrelotadas.size()][7];
        for (int i = 0; i < aulasSobrelotadas.size(); i++) {
            Aula aula = aulasSobrelotadas.get(i);
            String[] dataApresentavel = aula.getDataAula().getData().toString().split(" ");
            String dataAula = (dataApresentavel[0] + " " + dataApresentavel[1] + " " + dataApresentavel[2] + " " + dataApresentavel[5]);
            data[i][0] = dataAula;
            data[i][1] = aula.getDataAula().getHoraInicio() + " - " + aula.getDataAula().getHoraFim();
            data[i][2] = aula.getUc().getNomeUC();
            data[i][3] = aula.getTurno();
            data[i][4] = aula.getSala();
            data[i][5] = aula.getLotacao();
            data[i][6] = aula.getNumInscritos();
        }

        JTable table = new JTable(data, columnNames);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        dialog.getContentPane().add(scrollPane);

        int sobrelotadas = aulasSobrelotadas.size();

        JLabel percentLabel = new JLabel("NºAulas sobrelotadas: " + sobrelotadas);
        percentLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        dialog.getContentPane().add(percentLabel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    /**
     * Devolve uma lista de todas as Aulas. Verifica se numInscritos é maior que lotacao para cada Aula e se for adiciona
     * a Aula a um novo array chamado aulasSobreLotadas.
     *
     * @return uma lista de Aulas que estão com excesso de alunos
     */
    public static List<Aula> getAulasSobreLotadas(List<Aula> aulaList) {
        List<Aula> aulasSobrelotadas = new ArrayList<>();
        // Iterar através de cada Aula e adicioná-la a aulasSobreLotadas se estiver com excesso de lotação
        for (Aula aula : aulaList) {
            // Ignorar salas com lotação -1 porque -1 quer dizer que o CSV nao tinha essa informação
            if (aula.getNumInscritos() > aula.getLotacao() && aula.getLotacao() != -1) {
                aulasSobrelotadas.add(aula);
            }
        }
        return aulasSobrelotadas;
    }


    //so testar: quando aulas sobrepostas sao null, quand ficam vazias, quando tem aulas sobrepostas, e quando aulas é null?
    public static List<Aula> getSobreposicoes(List<Aula> aulaList) {
        List<Aula> aulasSobrepostas = new ArrayList<>();
        for (int i = 0; i < aulaList.size() - 1; i++) {
            for (int j = i + 1; j < aulaList.size(); j++) {
                if (aulaList.get(i).compareTo(aulaList.get(j)) == 0) {
                    aulasSobrepostas.add(aulaList.get(i));
                    aulasSobrepostas.add(aulaList.get(j));
                }
            }
        }
        return aulasSobrepostas;
    }
}
