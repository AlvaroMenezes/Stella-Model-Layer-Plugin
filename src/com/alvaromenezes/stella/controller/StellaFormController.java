package com.alvaromenezes.stella.controller;

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

        if (view.txtURL.getText().isEmpty()) {
            generateByFile();
        } else {

            generateByURL();
        }

        view.showMessage("Done!");

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

        //ModelCreator creator = new ModelCreator(json);
        //creator.create();

    }

    private void generateByURL() {

       // String urlX = "https://publicobject.com/helloworld.txt";
        String url = "http://api.wunderground.com/api/57dd9039b81a9c21/conditions/q/CA/San_Francisco.json";

        ProgressDialog dialog = new ProgressDialog();
        dialog.setSize(240, 329);
        dialog.setLocationRelativeTo(view.panelMain);
        dialog.setDlgTitle("Downloading");


        DownloadTask task = new DownloadTask(url, dialog);
        task.execute();

        dialog.setVisible(true);

    }

}
