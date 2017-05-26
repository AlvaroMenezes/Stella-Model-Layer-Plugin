package com.alvaromenezes.stella.controller;

import com.alvaromenezes.stella.util.FileUtil;
import com.alvaromenezes.stella.view.StellaForm;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

/**
 * Created by alvaromenezes on 5/26/17.
 */
public class StellaFormController {


    private StellaForm view;


    public StellaFormController(StellaForm view) {

        this.view = view;


    }


    public void setPath() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose a JSON file");
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnValue = jfc.showOpenDialog(view.panelMain);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            view.txtPath.setText(jfc.getSelectedFile().getAbsolutePath());
        }
    }


    private boolean isEmpty(){

      return   view.txtPath.getText().trim().isEmpty() &&
                    view.txtURL.getText().trim().isEmpty();
    }



    public void generate(){

        if(isEmpty()){
            showMessage("Add an URL or a file path!", "Stella Model Layer");
            return;
        }

        generateByFile();

    }

    private void generateByFile(){

        String path = view.txtPath.getText().trim();

        FileUtil util = new FileUtil();

        if(!util.hasFile(path)){
            showMessage("File not found!", "Stella Model Layer");
            return;
        }

        String json = "";

        try {
             json = util.readFile(path);
        } catch (Exception e) {
            showMessage("ERROR: "+ e.getMessage(), "Stella Model Layer");
        }


        ModelCreator creator = new ModelCreator(json);
        creator.create();


    }

    private void generateByURL(){



    }



    private void showMessage(String message, String title){

        ImageIcon ic = new ImageIcon("/icons/star_icon.png");

        JOptionPane.showMessageDialog(view.panelMain,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE,
                ic);


    }



}
