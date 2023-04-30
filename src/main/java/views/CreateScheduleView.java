package views;

import controllers.CreateScheduleController;
import controllers.ViewController;
import models.Aula;
import models.UnidadeCurricular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.*;
import java.util.List;

public class CreateScheduleView extends View {

    private static final Logger logger = LoggerFactory.getLogger(CreateScheduleView.class);

    public CreateScheduleView(ViewController viewController) {
        super(viewController);
    }

    private void showCreateUC() {
        // Create the panel to display the checkboxes
        JPanel checkboxPanel = new JPanel(new GridLayout(0, 1));
        Set<UnidadeCurricular> unidadesCurriculares = viewController.getHorario().getUnidadesCurriculares();
        List<UnidadeCurricular> sortedList = new ArrayList<>(unidadesCurriculares);
        sortedList.sort(Comparator.comparing(UnidadeCurricular::getNomeUC));
        Set<UnidadeCurricular> selectedUnits = new HashSet<>();
        for (UnidadeCurricular uc : sortedList) {
            JCheckBox checkBox = new JCheckBox(uc.getNomeUC());
            checkBox.addItemListener(e -> {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedUnits.add(uc);
                } else {
                    selectedUnits.remove(uc);
                }
            });
            checkboxPanel.add(checkBox);
        }

        // Create a new dialog for the popup and add the checkboxes and save button to it
        JDialog dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        dialog.add(new JScrollPane(checkboxPanel), BorderLayout.CENTER);
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {
            // Save the selected units and close the dialog
            ((CreateScheduleController)viewController).setSelectedUnits(selectedUnits);
            dialog.dispose();
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


    private void showChooseTurno(){
        Set<UnidadeCurricular> selectedUnits =  ((CreateScheduleController)viewController).getSelectedUnits();
        if(selectedUnits.isEmpty()){
            JOptionPane.showMessageDialog(null,
                    "Erro! Ainda não escolheu as Unidades Curriculares",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Create the panel to display the checkboxes
        JPanel checkboxPanel = new JPanel(new GridLayout(0, 1));
        List<Aula> selectedAulas = new ArrayList<>();
        for (UnidadeCurricular uc : selectedUnits) {
            for(Aula a : uc.getAulas()){
                JCheckBox checkBox = new JCheckBox("UC: " + uc.getNomeUC() + " | Turno: " + a.getTurno() +
                        " | Dia da semana: "
                        + a.getDataAula().getDiaSemana() + " | Hora início: " + a.getDataAula().getHoraInicio() +
                        " | Hora fim: " + a.getDataAula().getHoraFim());
                checkBox.addItemListener(e -> {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        selectedAulas.add(a);
                    } else {
                        selectedAulas.remove(a);
                    }
                });
                checkboxPanel.add(checkBox);
            }
        }

        // Create a new dialog for the popup and add the checkboxes and save button to it
        JDialog dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        dialog.add(new JScrollPane(checkboxPanel), BorderLayout.CENTER);
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {
            // Save the selected units and close the dialog
            ((CreateScheduleController)viewController).setSelectedAulas(selectedAulas);
            dialog.dispose();
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


    @Override
    public void initFrame() {
        this.removeAll();
        JButton criarUc = new JButton("Escolher Unidades Curriculares");
        criarUc.addActionListener(e -> showCreateUC());
        JButton escolherTurnos = new JButton("Escolher turnos");
        escolherTurnos.addActionListener(e -> showChooseTurno());
        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());

        add(criarUc);
        add(escolherTurnos);
        add(backBtn);
    }
}
