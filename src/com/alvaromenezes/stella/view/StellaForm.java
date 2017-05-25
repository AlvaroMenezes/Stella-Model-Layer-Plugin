package com.alvaromenezes.stella.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alvaromenezes on 5/17/17.
 */
public class StellaForm implements ActionListener{


    JTextField txtPath;
    JTextField txtURL;
    public JPanel panelMain;
    private JButton btnPath;
    private JButton btnGenerate;

    public StellaForm() {
        btnGenerate.addActionListener(this);
        btnPath.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnGenerate){
            onGenerate();
        }else if(e.getSource() == btnPath) {
            setFilePath();
        }
    }

    private void setFilePath() {
    }

    private void onGenerate() {

    }
}
