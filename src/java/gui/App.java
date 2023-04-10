package gui;

import views.MainMenuView;
import views.UploadFilesView;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {
    private final JFrame frame;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;
    private final MainMenuView mainMenuView;
    private final UploadFilesView uploadFilesView;
    private final ViewController viewController;

    public App() {
        // Initialize the frame and main panel
        this.frame = new JFrame("Calendar App");
        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel();
        // Add each view panel to the main panel with a unique identifier
        mainPanel.setLayout(cardLayout);

        // Initialize the controller with the views
        this.viewController = new ViewController(mainPanel, frame);

        // Initialize the views
        this.mainMenuView = new MainMenuView(viewController);
        this.uploadFilesView = new UploadFilesView(viewController);

        mainPanel.add(mainMenuView, "mainMenuView");
        mainPanel.add(uploadFilesView, "uploadFilesView");

        // Add the main panel to the frame and set the frame properties
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        showView("uploadFilesView");
    }

    protected JFrame getFrame(){
        return frame;
    }

    public void showView(String viewName) {
        cardLayout.show(mainPanel, viewName);
    }

    public static void main(String[] args) {
        App app = new App();
    }
}
