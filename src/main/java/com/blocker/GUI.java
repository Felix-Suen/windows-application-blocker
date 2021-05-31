package com.blocker;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import javax.swing.AbstractButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Timer;

public class GUI implements ActionListener {
    private Timer timer = new Timer();
    private App app = new App();
    public static void main(String[] args) {
        JFrame frame = new JFrame("App Blocker");
        frame.setTitle("App Blocker");
        JPanel panel = new JPanel();

        frame.setSize(350, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        // username
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        //password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

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
