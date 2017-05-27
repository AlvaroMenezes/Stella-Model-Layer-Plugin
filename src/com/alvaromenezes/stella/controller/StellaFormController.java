package com.alvaromenezes.stella.controller;

import com.alvaromenezes.stella.util.FileUtil;
import com.alvaromenezes.stella.view.StellaForm;

import java.io.IOException;

/**
 * Created by alvaromenezes on 5/26/17.
 */
public class StellaFormController {


    private StellaForm view;


    public StellaFormController(StellaForm view) {

        this.view = view;

    }


    private boolean isEmpty() {

        return view.txtPath.getText().trim().isEmpty() &&
                view.txtURL.getText().trim().isEmpty();
    }


    public void generate() {


        if (isEmpty()) {
            view.showMessage("Add an URL or a file path!");
            return;
        }

        if(view.txtURL.getText().isEmpty()) {
            generateByFile();
        }else {

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

        ModelCreator creator = new ModelCreator(json);
        creator.create();

    }

    private void generateByURL() {

        String url = "http://api.wunderground.com/api/57dd9039b81a9c21/conditions/q/CA/San_Francisco.json";
        Connection conn = new Connection();

        String json = "";

        try {
           // json = conn.getUrlData(view.txtURL.getText().trim());
            json = conn.getUrlData(url);
        } catch (IOException e) {
            view.showMessage("Error: " + e.getMessage());
            return;
        }

        ModelCreator creator = new ModelCreator(json);
        creator.create();

    }


}
