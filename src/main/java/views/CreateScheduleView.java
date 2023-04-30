package views;

import controllers.CreateScheduleController;
import controllers.ShowScheduleController;
import controllers.ViewController;
import models.Aula;
import models.UnidadeCurricular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.exporter.FileExporter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.*;
import java.util.List;

import static controllers.ViewController.setContent;

public class CreateScheduleView extends View {

    private static final Logger logger = LoggerFactory.getLogger(CreateScheduleView.class);

    public CreateScheduleView(ViewController viewController) {
        super(viewController);
    }

    private void showCreateUC() {
        // Create the list to display the checkboxes
        JList<UnidadeCurricular> ucList = new JList<>();
        ucList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        ucList.setCellRenderer(new UCCheckBoxListRenderer());

        DefaultListModel<UnidadeCurricular> listModel = new DefaultListModel<>();
        Set<UnidadeCurricular> unidadesCurriculares = viewController.getHorario().getUnidadesCurriculares();
        List<UnidadeCurricular> sortedList = new ArrayList<>(unidadesCurriculares);
        sortedList.sort(Comparator.comparing(UnidadeCurricular::getNomeUC));
        for (UnidadeCurricular uc : sortedList) {
            listModel.addElement(uc);
        }
        ucList.setModel(listModel);

        JLabel instructionsLabel = new JLabel("" +
                "Selecione uma ou mais Unidades Curriculares (use Ctrl + clique para selecionar várias):");

        // Create a new dialog for the popup and add the checkboxes and save button to it
        JDialog dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        dialog.add(instructionsLabel, BorderLayout.NORTH);
        dialog.add(new JScrollPane(ucList), BorderLayout.CENTER);
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {
            // Save the selected units and close the dialog
            Set<UnidadeCurricular> selectedUnits = new HashSet<>(ucList.getSelectedValuesList());
            ((CreateScheduleController) viewController).setSelectedUnits(selectedUnits);
            dialog.dispose();
            showChooseTurno();
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveBtn);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setTitle("Escolher Unidades Curriculares");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void showChooseTurno() {
        Set<UnidadeCurricular> selectedUnits = ((CreateScheduleController) viewController).getSelectedUnits();
        if (selectedUnits.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Erro! Ainda não escolheu as Unidades Curriculares",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a list to hold the checkboxes
        DefaultListModel<Aula> listModel = new DefaultListModel<>();
        for (UnidadeCurricular uc : selectedUnits) {
            for (Aula a : uc.getAulas()) {
                listModel.addElement(a);
            }
        }

        // Create a new list with a custom renderer
        JList<Aula> aulasList = new JList<>(listModel);
        aulasList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        aulasList.setCellRenderer(new AulaCheckBoxListRenderer());

        JLabel instructionsLabel = new JLabel("" +
                "Selecione uma ou mais Unidades Curriculares (use Ctrl + clique para selecionar várias):");

        // Create a new dialog for the popup and add the list and save button to it
        JDialog dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        dialog.add(instructionsLabel, BorderLayout.NORTH);
        dialog.add(new JScrollPane(aulasList), BorderLayout.CENTER);
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {
            // Save the selected units and close the dialog
            ((CreateScheduleController) viewController).setSelectedAulas(aulasList.getSelectedValuesList());
            ((CreateScheduleController) viewController).createHorario();
            dialog.dispose();
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveBtn);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setTitle("Escolher os turnos");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void showChooseFileFormat(){
        // Create a new dialog for the popup and add the checkboxes and save button to it
        JDialog dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        JButton toCsv = new JButton("Exportar para CSV");
        JButton toJson = new JButton("Exportar para JSON");

        toCsv.addActionListener(e -> {
            dialog.dispose();
            //TODO chamar funcao para mostrar o dialog do export e passar o string do conteudo
            String content = FileExporter.horarioToCsv(((CreateScheduleController) viewController).getSelectedHorario());
            setContent(content);
            viewController.showExportFilesView();
        });

        toJson.addActionListener(e -> {
            dialog.dispose();
            //TODO chamar funcao para mostrar o dialog do export e passar o string do conteudo
            String content = FileExporter.horarioToCsv(((CreateScheduleController) viewController).getSelectedHorario());
            setContent(content);
            viewController.showExportFilesView();
        });




        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(toCsv);
        buttonPanel.add(toJson);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setTitle("Escolher os turnos");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
        dialog.pack();
        dialog.setVisible(true);
    }


    @Override
    public void initFrame() {
        this.removeAll();
        JButton criarUc = new JButton("Criar horário");
        criarUc.addActionListener(e -> showCreateUC());
        JButton verHorario = new JButton("Visualizar horário");
        verHorario.addActionListener(e ->{
            List<Aula> selectedAulas = ((CreateScheduleController) viewController).getSelectedAulas();
            ShowScheduleController.createHtmlView(selectedAulas);
        }
        );
        JButton guardarBtn = new JButton("Guardar Horário");
        guardarBtn.addActionListener(e -> showChooseFileFormat());
        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());

        add(criarUc);
        add(verHorario);
        add(guardarBtn);
        add(backBtn);
    }

    static class UCCheckBoxListRenderer extends JCheckBox implements ListCellRenderer<UnidadeCurricular> {
        @Override
        public Component getListCellRendererComponent(JList<? extends UnidadeCurricular> list, UnidadeCurricular uc, int index, boolean isSelected, boolean cellHasFocus) {
            setText(uc.getNomeUC());
            setSelected(isSelected);
            setEnabled(list.isEnabled());
            return this;
        }
    }

    static class AulaCheckBoxListRenderer extends JCheckBox implements ListCellRenderer<Aula> {
        @Override
        public Component getListCellRendererComponent(JList<? extends Aula> list, Aula aula, int index, boolean isSelected, boolean cellHasFocus) {
            setText("UC: " + aula.getUc().getNomeUC() + " | Turno: " + aula.getTurno() +
                    " | Dia da semana: "
                    + aula.getDataAula().getDiaSemana() + " | Hora início: " + aula.getDataAula().getHoraInicio() +
                    " | Hora fim: " + aula.getDataAula().getHoraFim());
            setSelected(isSelected);
            setEnabled(list.isEnabled());
            return this;
        }
    }

}
