package com.alvaromenezes.stella.view;

import com.alvaromenezes.stella.controller.StellaFormController;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by alvaromenezes on 5/17/17.
 */
public class StellaForm implements ActionListener,ItemListener {


    public JTextField txtPath;
    public JTextField txtURL;
    public JPanel panelMain;
    private JButton btnPath;
    private JButton btnGenerate;
    private JRadioButton rbtFile;
    private JRadioButton rbtRest;
    private ButtonGroup buttonGroup;


    public StellaForm() {
        buttonGroup = new ButtonGroup();
        buttonGroup.add(rbtFile);
        buttonGroup.add(rbtRest);

        rbtFile.addItemListener(this);
        rbtRest.addItemListener(this);

        btnGenerate.addActionListener(this);
        btnPath.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnGenerate) {
            onGenerate();
        } else if (e.getSource() == btnPath) {
            setFilePath();
        }
    }


    private void onGenerate() {


       ProgressDialog dialog = new ProgressDialog();
      //  dialog.pack();
        dialog.setLocationRelativeTo(panelMain);
        dialog.setVisible(true);


       // new StellaFormController(this).generate();

    }


    public void setFilePath() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose a JSON file");
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = jfc.showOpenDialog(panelMain);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            txtPath.setText(jfc.getSelectedFile().getAbsolutePath());
        }
    }


    public void showMessage(String message) {
        showMessage(message, "Stella Model layer");
    }

    public void showMessage(String message, String title) {

        ImageIcon ic = new ImageIcon("/icons/star_icon.png");

        JOptionPane.showMessageDialog(panelMain,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE,
                ic);

    }

    @Override
    public void itemStateChanged(ItemEvent e) {


        if(rbtRest.isSelected()){

            txtPath.setText("");
            txtURL.setText("");

            txtPath.setEnabled(false);
            txtURL.setEnabled(true);

        }else if(rbtFile.isSelected()){
            txtPath.setText("");
            txtURL.setText("");
            txtPath.setEnabled(true);
            txtURL.setEnabled(false);
        }

    }
}
