package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import javax.swing.*;


public class ShowScreen {

    private JFrame frame;

    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel xyPanel;

    private JLabel xLabel;
    private JLabel yLabel;
    private JLabel iterations;
    private JLabel file;
    private JLabel delay_default_10ms;

    private JTextField xTextField;
    private JTextField yTextField;
    private JTextField iterations_int;
    private JTextField delay_int;

    private JButton start;
    private JButton openFile;

    private JFileChooser fileChooser;

    private ShowScreen(){
        mainPanel = new JPanel();

        frame = new JFrame();
        frame.setSize(700, 500);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bite - all recipes in one place!");

        mainPanel.setLayout(new GridLayout(1, 2));
        leftPanel = new JPanel(new GridLayout(10, 0));
        leftPanel.setSize(500, 20); // nie działa?
        showLeftPanel();

        frame.setVisible(true);
    }

    private void showLeftPanel(){
        xyPanel = new JPanel(new GridLayout(2,2));

        xLabel = new JLabel("Width: ");
        yLabel = new JLabel("Height: ");

        xTextField = new JTextField();
        yTextField = new JTextField();

        xyPanel.add(xLabel);
        xyPanel.add(xTextField);
        xyPanel.add(yLabel);
        xyPanel.add(yTextField);

        iterations = new JLabel("No. of iterations");
        file = new JLabel("Choose file");
        delay_default_10ms = new JLabel("Delay (default: 10ms)");

        iterations_int = new JTextField();
        iterations_int.setMaximumSize(new Dimension(10, 20));// nie działa?
        delay_int = new JTextField();

        start = new JButton("START");

        fileChooser = new JFileChooser();

        openFile = new JButton("Open...");

        openFile.addActionListener(new ActionListener(){
            @Override
           public void actionPerformed(ActionEvent actionEvent){
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(ShowScreen.this.leftPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                }
            }
        });

        leftPanel.add(xyPanel);
        leftPanel.add(iterations);
        leftPanel.add(iterations_int);
        leftPanel.add(file);
        leftPanel.add(openFile);
        leftPanel.add(delay_default_10ms);
        leftPanel.add(delay_int);
        leftPanel.add(start);

        mainPanel.add(leftPanel);

    }


    public static void main(String[] args) {
        new ShowScreen();
    }


}


