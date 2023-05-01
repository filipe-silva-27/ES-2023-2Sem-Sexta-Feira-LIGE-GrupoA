package utils.URI;
import models.Horario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

    public class ImportButton extends JPanel {
        private JTextField uriField;
        private JButton importButton;

        /**
         * Constructs an ImportButton with the specified text for the import button.
         * @param buttonText the text to display on the import button
         */
        public ImportButton(String buttonText) {
            super(new FlowLayout());

            uriField = new JTextField(20);
            add(uriField);

            importButton = new JButton(buttonText);
            /*importButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String uri = uriField.getText();
                    try {
                        Horario horario = Horario.create("Horario", uri);
                        // TODO: Handle successful import of Horario object
                    } catch (IOException | URISyntaxException ex) {
                        // TODO: Handle error importing Horario object
                        ex.printStackTrace();
                    }
                }
            });*/
            add(importButton);
        }

        // Example usage of ImportButton
        public static void main(String[] args) {
            JFrame frame = new JFrame("Import Button Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ImportButton importButton = new ImportButton("Import");
            frame.getContentPane().add(importButton);//ou new importButton

            frame.pack();
            frame.setVisible(true);
        }
    }


