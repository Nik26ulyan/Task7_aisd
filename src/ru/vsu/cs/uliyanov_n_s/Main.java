package ru.vsu.cs.uliyanov_n_s;

import ru.vsu.cs.uliyanov_n_s.demo.GraphDemoFrame;
import ru.vsu.cs.uliyanov_n_s.utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

import static java.awt.Frame.MAXIMIZED_BOTH;

public class Main {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        SwingUtils.setDefaultFont("Arial", 20);

        EventQueue.invokeLater(() -> {
            try {
                JFrame mainFrame = new GraphDemoFrame();
                mainFrame.setVisible(true);
                mainFrame.setExtendedState(MAXIMIZED_BOTH);
            } catch (Exception ex) {
                SwingUtils.showErrorMessageBox(ex);
            }
        });
    }
}
