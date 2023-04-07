package gui;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 * Classe Home que é o da página principal Java que interage com o utilizador final
 * @author Mário Cao
 */

public class Home extends Screen{

    public Home(String title) {
        super(title);
    }

    /**
     * Inicializa a interface gráfica do utilizador.
     */
    public void init(){
        // Cria o painel para os botões
        JPanel panel = new JPanel();

        // adiciona os botoes ao painel
        panel.add(gui.importBtn);
        panel.add(gui.criarBtn);

        // Adiciona o painel ao contentor principal da janela
        contentPanel.add(panel, BorderLayout.CENTER);

        contentPanel.revalidate();
    }

}
