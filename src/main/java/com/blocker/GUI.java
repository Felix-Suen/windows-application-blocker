package com.blocker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Timer;

public class GUI implements ActionListener {
    private Timer timer = new Timer();
    private App app = new App();
    public static void main(String[] args) {
        JFrame frame = new JFrame("App Blocker");
        frame.setTitle("App Blocker");

        frame.setSize(350, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);
        panel.setPreferredSize(new Dimension(250, 250));

        // title
        String text = "Application Blocker";
        JLabel title = new JLabel("<html><div style='text-align: center;'>" + text + "</div></html>");
        panel.add(title);

        //button
        JToggleButton button = new JToggleButton("Block");
        button.setBounds(10, 80, 80, 25);
        button.addActionListener(new GUI());
        panel.add(button);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractButton abstractButton = (AbstractButton)e.getSource();
        boolean selected = abstractButton.getModel().isSelected();

        if (selected) {
            timer.schedule(app, 0, 5000);
        }
        else {
            System.out.println("not selected");
            app.cancel();
            timer.cancel();
            timer.purge();
        }

    }
}
