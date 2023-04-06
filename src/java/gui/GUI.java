package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GUI {

    public JFrame getFrame() {
        return frame;
    }

    public Container getContentPanel() {
        return contentPanel;
    }

    /**
     * classe com componentes da GUI que podem ser reutilizados
     */

    private final JFrame frame;
    private final Container contentPanel;

    protected final JButton criarBtn = new JButton("Criar ficheiro");
    protected final JButton importBtn = new JButton("Importar ficheiro");
    protected final JButton convertBtn = new JButton("Converter ficheiro");
    protected final JButton visualizarBtn = new JButton("Visualizar ficheiro");
    private final JButton exportBtn = new JButton("Exportar ficheiro");
    private final JButton backBtn = new JButton("Voltar");

    protected GUI(String title) {
        frame = new JFrame(title);

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

    public abstract void init();



}
