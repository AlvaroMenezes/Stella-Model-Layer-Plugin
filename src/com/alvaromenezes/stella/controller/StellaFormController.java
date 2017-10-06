package com.alvaromenezes.stella.controller;

import com.alvaromenezes.stella.creator.ModelCreator;
import com.alvaromenezes.stella.util.FileUtil;
import com.alvaromenezes.stella.view.ProgressDialog;
import com.alvaromenezes.stella.view.StellaForm;
import com.intellij.openapi.project.Project;

/**
 * Created by alvaromenezes on 5/26/17.
 */
public class StellaFormController {


    private StellaForm view;
    private static Project project;


    public StellaFormController(Project project) {

        this.project = project;
    }

    public static Project getProject() {
        return project;
    }

    private boolean isEmpty() {

        return view.txtPath.getText().trim().isEmpty() &&
                view.txtURL.getText().trim().isEmpty();
    }

    public void setView(StellaForm view) {
        this.view = view;

    }


    public void generate() {

        if (isEmpty()) {
            view.showMessage("Add an URL or a file path!");
            return;
        }

        if(view.rbtFile.isSelected()) {
            generateByFile();
        } else if(view.rbtRest.isSelected()) {
            generateByURL();
        }



    }

    private void generateByFile() {

        String path = view.txtPath.getText().trim();

        FileUtil util = new FileUtil();

        if (!util.hasFile(path)) {
            view.showMessage("File not found!");
            return;
        }

        String json = "";

        try {
            json = util.readFile(path);
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
            return;
        }



        ProgressDialog dialog = getDialog("Reading file");
        ProcessResponseTask task = new ProcessResponseTask(json, dialog);
        task.execute();
        dialog.setVisible(true);


    }

    private ProgressDialog getDialog(String title){
        ProgressDialog dialog = new ProgressDialog();
        dialog.setSize(240, 329);
        dialog.setLocationRelativeTo(view.panelMain);
        dialog.setDlgTitle(title);
        return dialog;
    }

    private void generateByURL() {

        ProgressDialog dialog = getDialog("Downloading");
        DownloadTask task = new DownloadTask(view.txtURL.getText(), dialog);
        task.execute();
        dialog.setVisible(true);

    }

}
