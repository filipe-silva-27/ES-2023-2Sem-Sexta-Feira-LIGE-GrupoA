package views;

import controllers.CreateScheduleController;
import controllers.ViewController;
import models.UnidadeCurricular;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.HashSet;
import java.util.Set;

public class CreateScheduleView extends View {

    private static final Logger logger = LoggerFactory.getLogger(CreateScheduleView.class);

    public CreateScheduleView(ViewController viewController) {
        super(viewController);
    }

    public void showCreateUC() {
        Set<UnidadeCurricular> selectedUnits = new HashSet<>();
        // Create the panel to display the checkboxes
        JPanel checkboxPanel = new JPanel(new GridLayout(0, 1));
        Set<UnidadeCurricular> unidadesCurriculares = viewController.getHorario().getUnidadesCurriculares();
        for (UnidadeCurricular uc : unidadesCurriculares) {
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


    @Override
    public void initFrame() {
        this.removeAll();
        JButton criarUc = new JButton("Escolher Unidades Curriculares");
        criarUc.addActionListener(e -> showCreateUC());
        JButton backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> viewController.showMainMenuView());

        add(criarUc);
        add(backBtn);
    }
}
