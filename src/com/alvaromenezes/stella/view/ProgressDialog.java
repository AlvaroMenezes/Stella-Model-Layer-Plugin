package com.alvaromenezes.stella.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgressDialog extends JDialog implements ProgressListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lblTitle;
    private JProgressBar progressBar;
    private JLabel lblAction;
    private String title;

    private Timer timer;

    public ProgressDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        Dimension d = progressBar.getPreferredSize();
        d.height = 30;
        progressBar.setPreferredSize(d);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {

    }

    @Override
    public void dispose() {
        if (timer != null) {
            timer.stop();
        }
        super.dispose();
    }


    @Override
    public void update(long bytesRead, long contentLength) {

        int size = (int) ((100 * bytesRead) / contentLength);

        progressBar.setValue(size);
        progressBar.setString(String.format("%d%%", size));
    }


    @Override
    public void setDlgTitle(String title) {

        this.title = title;
        lblTitle.setText(title);

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(300, new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {

                switch (count) {
                    case 0:
                        lblTitle.setText(title);
                        ++count;
                        break;
                    case 1:
                        lblTitle.setText(title + ".");
                        ++count;
                        break;
                    case 2:
                        lblTitle.setText(title + "..");
                        ++count;

                        break;
                    case 3:
                        lblTitle.setText(title + "...");
                        count = 0;
                        break;
                }
            }
        });

        timer.start();
    }

    @Override
    public void setAction(String action) {
        lblAction.setText(action);

    }
}
