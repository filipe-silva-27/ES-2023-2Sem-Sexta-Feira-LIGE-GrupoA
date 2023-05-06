package views;

import controllers.CreateScheduleController;
import controllers.ShowScheduleController;
import controllers.ViewController;
import models.Aula;
import models.UnidadeCurricular;
import org.junit.Ignore;
import utils.DetalhesAulasDialog;
import utils.exporter.FileExporter;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static controllers.ViewController.setContent;

/**
 * Classe view da criação de horário e a sua visualização
 * @see View
 */
public class CreateScheduleView extends View {

    JButton criarUc;
    JButton verHorario;
    JButton verSobrelotacao;
    JButton verSobreposicoes;
    JButton guardarBtn;
    JButton backBtn;

    /**
     * Método construtor
     * @param viewController ViewController
     * @see ViewController
     */
    public CreateScheduleView(ViewController viewController) {
        super(viewController);
    }

    /**
     * Função que inicializa os botões
     */
    @Ignore
    private void initButtons(){
        criarUc = new JButton("Personalizar horário");
        verHorario = new JButton("Visualizar horário");
        verSobrelotacao = new JButton("Ver sobrelotações");
        verSobreposicoes = new JButton("Ver sobreposições");
        guardarBtn = new JButton("Guardar Horário");
        backBtn = new JButton("Voltar");
    }

    /**
     * Função que inicializa os listeners
     */
    @Ignore
    private void initListeners(){
        criarUc.addActionListener(e -> {
            Set<UnidadeCurricular> unidadesCurriculares = viewController.getHorario().getUnidadesCurriculares();
            showCreateUC(unidadesCurriculares);
        });
        verHorario.addActionListener(e ->{
                    List<Aula> selectedAulas = ((CreateScheduleController) viewController).getSelectedAulas();
                    ShowScheduleController.createHtmlView(selectedAulas);
                }
        );
        verSobrelotacao.addActionListener(e -> {
            List<Aula> aulaList = ((CreateScheduleController) viewController).getSelectedAulas();
            List<Aula> aulasSobrepostas = DetalhesAulasDialog.getSobreposicoes(aulaList);
            DetalhesAulasDialog.showSobreposicoesView(aulasSobrepostas);
        });
        verSobreposicoes.addActionListener(e ->{
            List<Aula> aulaList = ((CreateScheduleController) viewController).getSelectedAulas();
            List<Aula> aulasSobrelotadas = DetalhesAulasDialog.getAulasSobreLotadas(aulaList);
            DetalhesAulasDialog.showAulasSobrelotadasView(aulasSobrelotadas);
        });
        guardarBtn.addActionListener(e -> showChooseFileFormat());
        backBtn.addActionListener(e -> viewController.showMainMenuView());
    }

    /**
     * Função que inicializa a frame
     */
    @Override
    @Ignore
    public void initFrame() {
        this.removeAll();
        initButtons();
        initListeners();
        add(criarUc);
        add(verHorario);
        add(verSobrelotacao);
        add(verSobreposicoes);
        add(guardarBtn);
        add(backBtn);
    }

    /**
     * Função que abre um JDialog para o user escolher as Unidades Curriculares
     * @param unidadesCurriculares Set de unidadescurriculares disponiveis para serem escolhidas
     * @see JDialog
     * @see DefaultListModel
     * @see UCCheckBoxListRenderer
     */
    @Ignore
    private void showCreateUC(Set<UnidadeCurricular> unidadesCurriculares) {

        JList<UnidadeCurricular> ucList = new JList<>();
        ucList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        ucList.setCellRenderer(new UCCheckBoxListRenderer());

        DefaultListModel<UnidadeCurricular> listModel = new DefaultListModel<>();
        List<UnidadeCurricular> sortedList = new ArrayList<>(unidadesCurriculares);
        sortedList.sort(Comparator.comparing(UnidadeCurricular::getNomeUC));
        for (UnidadeCurricular uc : sortedList) {
            listModel.addElement(uc);
        }
        ucList.setModel(listModel);

        JLabel instructionsLabel = new JLabel("" +
                "Selecione uma ou mais Unidades Curriculares (use Ctrl + clique para selecionar várias):");

        JDialog dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        dialog.add(instructionsLabel, BorderLayout.NORTH);
        dialog.add(new JScrollPane(ucList), BorderLayout.CENTER);
        JButton saveBtn = new JButton("Save");

        saveBtn.addActionListener(e -> {
            Set<UnidadeCurricular> selectedUnits = new HashSet<>(ucList.getSelectedValuesList());
            ((CreateScheduleController) viewController).setSelectedUnits(selectedUnits);
            dialog.dispose();
            showChooseTurno();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(saveBtn);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setTitle("Escolher Unidades Curriculares");
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
        dialog.pack();
        dialog.setVisible(true);
    }


    /**
     * Função que abre um JDialog para o user escolher os turnos
     * @see JDialog
     * @see DefaultListModel
     * @see AulaCheckBoxListRenderer
     */
    @Ignore
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
                "Selecione um ou mais Turnos (use Ctrl + clique para selecionar várias):");

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
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
        dialog.pack();
        dialog.setVisible(true);
    }

    /**
     * Função que mostra um JDialog para escolher como guardar o ficheiro.
     * @see JDialog
     * @see CreateScheduleController
     */
    @Ignore
    private void showChooseFileFormat(){
        // Create a new dialog for the popup and add the checkboxes and save button to it
        JDialog dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        JButton toCsv = new JButton("Exportar para CSV");
        JButton toJson = new JButton("Exportar para JSON");

        toCsv.addActionListener(e -> {
            dialog.dispose();
            String content = FileExporter.horarioToCsv(((CreateScheduleController) viewController).getSelectedHorario());
            setContent(content);
            viewController.showExportFilesView();
        });

        toJson.addActionListener(e -> {
            dialog.dispose();
            String content = FileExporter.horarioToJson(((CreateScheduleController) viewController).getSelectedHorario());
            setContent(content);
            viewController.showExportFilesView();
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(toCsv);
        buttonPanel.add(toJson);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setTitle("Escolher os turnos");
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setModal(true);
        dialog.pack();
        dialog.setVisible(true);
    }


    /**
     * Classe que altera o look default dos JCheckbox
     * @see JCheckBox
     */
    @Ignore
    static class UCCheckBoxListRenderer extends JCheckBox implements ListCellRenderer<UnidadeCurricular> {
        @Override
        public Component getListCellRendererComponent(JList<? extends UnidadeCurricular> list,
                                                      UnidadeCurricular uc, int index, boolean isSelected, boolean cellHasFocus) {
            setText(uc.getNomeUC());
            setSelected(isSelected);
            setEnabled(list.isEnabled());
            return this;
        }
    }

    /**
     * Classe que altera o look default dos JCheckbox
     * @see JCheckBox
     */
    @Ignore
    static class AulaCheckBoxListRenderer extends JCheckBox implements ListCellRenderer<Aula> {
        @Override
        public Component getListCellRendererComponent(JList<? extends Aula> list, Aula aula, int index, boolean isSelected, boolean cellHasFocus) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateStr = dateFormat.format(aula.getDataAula().getData());
            setText("UC: " + aula.getUc().getNomeUC() + " | Turno: " + aula.getTurno() +
                    " | Data Aula: " + dateStr +
                    " | Dia da semana: "
                    + aula.getDataAula().getDiaSemana().getName() + " | Hora início: " + aula.getDataAula().getHoraInicio() +
                    " | Hora fim: " + aula.getDataAula().getHoraFim());
            setSelected(isSelected);
            setEnabled(list.isEnabled());
            return this;
        }
    }

}
