package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    /**
     * classe com componentes da GUI que podem ser reutilizados
     */

    private static GUI instance = null;

    private final JFrame frame;

    private final Container contentPanel;
    protected final JButton criarBtn = new JButton("Criar ficheiro");

    protected final JButton importBtn = new JButton("Importar ficheiro");
    protected final JButton convertBtn = new JButton("Converter ficheiro");
    protected final JButton visualizarBtn = new JButton("Visualizar ficheiro");
    private final JButton exportBtn = new JButton("Exportar ficheiro");
    private final JButton backBtn = new JButton("Voltar");

    protected GUI() {
        frame = new JFrame("Gestão de Horários");
        System.out.println(
                "GUI: " + frame.getName() + " " + frame.getSize() + " " + frame.getLocation() + " " + frame.isVisible()
        );
        // Inicializa o painel de conteudo
        contentPanel = frame.getContentPane();
        // Define o tamanho da janela
        frame.setSize(400, 300);

        // Define o comportamento da janela quando fechar
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Define a janela como visível
        frame.setVisible(true);

        setButtons();

    }

    //singleton getInstance method
    public static GUI getInstance() {
        System.out.println(instance);
        if (instance == null) {
            System.out.println("GUI: Creating new instance");
            instance = new GUI();
        }
        return instance;
    }
    //public GUI() { }

    public JFrame getFrame() {
        return frame;
    }

    public Container getContentPanel() {
        return contentPanel;
    }

    private void setButtons() {
        importBtn.addActionListener(new ActionListener() {
            /**
             * Mostra o sub-menu de import de ficheiros
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                new ImportMenu("Importar ficheiro");
            }
        });
        criarBtn.addActionListener(new ActionListener() {
            /**
             * Mostra o sub-menu de import de ficheiros
             * @param e the event to be processed
             */
            public void actionPerformed(ActionEvent e) {
                //new CriarMenu();
                //FIXME: Criar classe de menu de criar horário
            }
        });
    }

    public void init(){
        System.out.println("GUI: init");
        new Home("home");
    }



}
