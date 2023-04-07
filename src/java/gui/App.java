package gui;

import java.util.Stack;

public class App {

    public static final Stack<Screen> SCREENS = new Stack<>();

    public static void main(String[] args) {
        GUI gui = GUI.getInstance();
        gui.init();
    }
}
