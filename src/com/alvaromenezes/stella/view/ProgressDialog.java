package com.alvaromenezes.stella.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lblTitle;
    private JProgressBar progressBar;
    private JLabel lblAction;

    public ProgressDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    public static void main(String[] args) {
        ProgressDialog dialog = new ProgressDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
